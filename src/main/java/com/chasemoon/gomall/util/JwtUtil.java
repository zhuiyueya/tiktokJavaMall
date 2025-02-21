package com.chasemoon.gomall.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private  String secret;
    @Value("${jwt.expiration}")
    private  long expiration;

    public  SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


    public  String generateToken(int userId) {
        Date now=new Date();
        Date expireDate=new Date(now.getTime()+expiration);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(getSigningKey())
                .compact();
    }


    public  String validateToken(String token) {
        try{
            //log.info("validateToken begin: "+token);
            Claims claims=Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            //log.info("validateToken end: "+token);
            //返回ID
            return claims.getSubject();
        }catch (ExpiredJwtException | UnsupportedJwtException
        | MalformedJwtException | SignatureException | IllegalArgumentException e){
            throw new RuntimeException("Invalid token"+e.getMessage());
        }
    }
}
