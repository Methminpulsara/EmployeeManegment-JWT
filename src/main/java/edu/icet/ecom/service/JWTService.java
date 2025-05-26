package edu.icet.ecom.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.sql.Date;

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

    public String getJWTToken(){
        return Jwts.builder()
                .setSubject("methmin")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(secretKey)
                .compact();
    }

    public String getUserName(String token) {

        try {

            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch (Exception e){
            return null;
        }
    }

}
