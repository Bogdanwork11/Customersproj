package org.example.customes.service;

import org.springframework.beans.factory.annotation.Value;

import org.example.customes.role.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


//jwtservice - работает с jwt
@Service
public class JwtSvc {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generationToken(String username, Role role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role.name());

        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) //1 час-3 600 000 мс
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    public Role extractRole(String token) {
        String role = extractClaims(token).get("role", String.class);
        return Role.fromString(role);
    }

    public boolean isTokenValid(String token){
        Claims claims = extractClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.after(new Date());

    }



}
