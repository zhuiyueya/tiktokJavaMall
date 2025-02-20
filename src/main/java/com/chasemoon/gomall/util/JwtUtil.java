package com.chasemoon.gomall.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private static String secret;
    @Value("${jwt.expiration}")
    private static long expiration;

    public static SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


    public static String generateToken(int userId) {
        Date now=new Date();
        Date expireDate=new Date(now.getTime()+expiration);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(getSigningKey())
                .compact();
    }


    public static String validateToken(String token) {
        try{
            Claims claims=Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            //返回ID
            return claims.getSubject();
        }catch (ExpiredJwtException | UnsupportedJwtException
        | MalformedJwtException | SignatureException | IllegalArgumentException e){
            throw new RuntimeException("Invalid token"+e.getMessage());
        }
    }
}
