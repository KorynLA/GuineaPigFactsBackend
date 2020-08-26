package com.factsdemo.guineaPigFacts.services.implementations;
import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.services.JWTService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static io.jsonwebtoken.Jwts.parser;

/**
 * Uses HMAC hashing as we only want to sign the JWT and prevent manipulation from the client / during
 * transport
 */
public class JWTServiceImplementation implements JWTService {
    final private String secretString = "";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public void JWTService() {}
    public String generateJWT(User user) {
        SecretKey key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
        String jws = Jwts.builder()
                .setSubject(user.getUserName())
                .claim("role", user.getRoles())
                .setExpiration(Date.from(Instant.now().plus(50, ChronoUnit.MINUTES)))
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return jws;
    }

    public String getTokenPrefix() {
        return TOKEN_PREFIX;
    }
    public String getHeaderString() {
        return HEADER_STRING;
    }
    public String parseJWT(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
        String found = Jwts.parser().setSigningKey(key).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        return found;
    }

}
