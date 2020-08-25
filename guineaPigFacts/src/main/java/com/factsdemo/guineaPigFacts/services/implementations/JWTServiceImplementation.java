package com.factsdemo.guineaPigFacts.services.implementations;
import com.factsdemo.guineaPigFacts.models.User;
import com.factsdemo.guineaPigFacts.services.JWTService;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Uses HMAC hashing as we only want to sign the JWT and prevent manipulation from the client / during
 * transport
 */
public class JWTServiceImplementation implements JWTService {
    final private String secretString = "";
    public String generateJWT(User user) {
        SecretKey key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
        String jws = Jwts.builder()
                .setSubject(user.getUserName())
                .claim("role", user.getRole())
                .setExpiration(Date.from(Instant.now().plus(50, ChronoUnit.MINUTES)))
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return jws;
    }

}
