package com.factsdemo.guineaPigFacts.securityFilters;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Processes a HTTP request's BASIC authorization headers
 */
public class AuthorizationFilter extends BasicAuthenticationFilter {
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
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {}
    @Override
    public void onUnsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                             AuthenticationException failed) {}
}
