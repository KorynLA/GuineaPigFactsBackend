package com.factsdemo.guineaPigFacts.securityFilters;

import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.services.JWTService;
import com.factsdemo.guineaPigFacts.services.implementations.JWTServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Filter class that Authenticates based on username & password form submission
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Performs user authentication. It returns an authentication token for the user if successful, or returns
     * null if not finished. If it is unsuccessful it throws an AuthenticationException.
     * @param req
     * @param res
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            User userToAuthenticate = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userToAuthenticate.getUserName(), userToAuthenticate.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    @Override
    public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth)
            throws IOException {
            JWTService jwtService = new JWTServiceImplementation();
            String jwt = jwtService.generateJWT((User) auth.getPrincipal());
            res.addHeader(jwtService.getHeaderString(), jwtService.getTokenPrefix() + jwt);
    }
}
