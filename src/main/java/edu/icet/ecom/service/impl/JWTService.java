package edu.icet.ecom.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.sql.Date;

import java.util.Map;

@Service
public class JWTService {

    private final SecretKey secretKey;

    public JWTService() {

        try {
           SecretKey k = KeyGenerator
                   .getInstance("HmacSHA256")
                   .generateKey();
           secretKey = Keys.hmacShaKeyFor(k.getEncoded());
        }catch (Exception e){
            throw new RuntimeException("JWT secret key is not valid");
        }
    }
    public boolean isTokenValid(String token) {
        try {
            // Replace "your-secret-key" with the actual secret used to sign the token
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getJWTToken(String userName, Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(secretKey)
                .compact();
    }



    public String getUserName(String token) {
        Claims data = getTokenData(token);
        if (data==null){return null;}
        return data.getSubject();
    }

    public Object getFieldFromToken(String token, String key) {
        Claims data = getTokenData(token);
        if (data==null){
            return null;
        }
        return data.get(key);
    }

    private Claims getTokenData(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }

}
