package com.interview.ExpenseManagerAPI.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class AuthUtil {


    @Value("${jwt.secretKey}")
    private  String secratekey;

    public SecretKey secretKey(){
        return Keys.hmacShaKeyFor(secratekey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId" , user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*10))
                .signWith(secretKey())
                .compact();
    }


    public String generateUsernameFromToken(String bearerToken) {

        Claims payload = Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(bearerToken)
                .getPayload();

        return payload.getSubject();
    }
}
