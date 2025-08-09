package com.project.auth_service.jwt;

import com.project.auth_service.model.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long jwtRefreshExpiration;

    public String generateToken(UserDTO user) {
        return buildToken(user, jwtExpiration);
    }

    public String generateRefreshToken(UserDTO user) {
        return buildToken(user, jwtRefreshExpiration);
    }

    private String buildToken(UserDTO userDto, final long jwtExpiration) {

        if (userDto instanceof UserDTO user) {
            return Jwts.builder()
                    .subject(user.getNif())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                    .signWith(getSignInKey())
                    .compact();
        } else {
            throw new IllegalArgumentException("UserDetails must be an instance of UserModel");
        }
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getNifFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDTO user) {
        final String nif = getNifFromToken(token);
        return nif.equals(user.getNif()) && !isTokenExpired(token);
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }
}

