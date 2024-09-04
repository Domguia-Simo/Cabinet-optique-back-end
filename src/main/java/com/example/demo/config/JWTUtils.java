package com.example.demo.config;

import com.example.demo.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;

import java.security.Key;
import java.util.Date;

@Configuration
public class JWTUtils {

    private String secret_key = "122345566788995122345566788995122345566788995";
    private int jwt_expiration = 86_400_000;

    private Key getSigningKey(){
        byte[] keybytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keybytes);
    }

    public String generateToken(User user){
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwt_expiration))
                .signWith(getSigningKey())
                .compact();
        return token;
    }

    public String extractEmail(String token){
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build().parseClaimsJws(token).getBody().getSubject();
    }


}
