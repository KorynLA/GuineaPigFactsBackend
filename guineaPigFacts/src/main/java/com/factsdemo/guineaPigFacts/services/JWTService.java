package com.factsdemo.guineaPigFacts.services;

import com.factsdemo.guineaPigFacts.models.User;

/**
 * Class that creates the JWTs that will be given to the client after they are authenticated
 * 3 parts to a JWT: header (algorithm used), body(claims), signature(combination of header & body through
 *  algorithm in the header)
 *
 */
public interface JWTService {
    public String generateJWT(User user);
    public String getHeaderString();
    public String getTokenPrefix();

    //Need to parse JWT to verify it is a good token provided by client
    public String parseJWT(String token);
    //Need to parse JWT to find associated user authorization
    //Need to parse JWT to find if token is still valid (time wise)
}
