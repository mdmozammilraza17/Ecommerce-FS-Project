package com.ecommerce.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private Key getSignInKey ()
    {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken (UserDetails userDetails)
    {
        String role = userDetails.getAuthorities()
                .stream()
                .findFirst()
                .map(authority -> authority.getAuthority())
                .orElse("");

        if (role.startsWith("ROLE_"))
        {
            role = role.substring(5);
        }

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername (String token)
    {
        return extractAllClaims(token).getSubject();
    }

    private Date extractExpiration(String token)
    {
        return extractAllClaims(token).getExpiration();
    }

    public  long getExpiration ()
    {
        return jwtExpiration;
    }

    public boolean validateToken (String token, UserDetails userDetails)
    {
        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    public boolean isTokenExpired (String token)
    {
        return extractExpiration(token).before(new Date());
    }
}
