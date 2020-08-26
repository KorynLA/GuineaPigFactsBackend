package com.factsdemo.guineaPigFacts.securityFilters;

import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.services.UserService;
import com.factsdemo.guineaPigFacts.services.implementations.JWTServiceImplementation;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Processes a HTTP request's BASIC authorization headers
 */
public class AuthorizationFilter extends BasicAuthenticationFilter {
    JWTServiceImplementation jwtService = new JWTServiceImplementation();
    @Autowired
    UserService userService;

    public AuthorizationFilter(AuthenticationManager authenticationManager) {

        super(authenticationManager);
    }

    /**
     * Stores a request attribute to filter. It is invoked once per request. This is the filter
     * that verifies the JWT given in the request header.
     * @param req
     * @param res
     * @param chain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(jwtService.getHeaderString());

        if (header == null || !header.startsWith(jwtService.getTokenPrefix())) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String token = req.getHeader(jwtService.getHeaderString());
        User findUser;
        String user;
        if (token != null) {
            // parse the token.
            user = jwtService.parseJWT(token);
            findUser = userService.findByUserName(user);

            if (user != null && findUser != null) {
                // new arraylist means authorities
                return new UsernamePasswordAuthenticationToken(user,
                        null,
                        new ArrayList<>());
            }

            return null;
        }

        return null;
    }
}
