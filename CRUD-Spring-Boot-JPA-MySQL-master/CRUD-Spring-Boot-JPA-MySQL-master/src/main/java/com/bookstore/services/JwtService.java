package com.bookstore.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71340101";

    private String createTokenResetPassword(Map<String, Object> claims, String userName) {    	
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    public String generateTokenResetPassword(String userName){
        Map<String, Object> claims = new HashMap<>();
        return createTokenResetPassword(claims, userName);
    }
    
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 8))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    @SuppressWarnings("deprecation")    
    public String getUserNameJwt(String token){
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String validateToken(String token) {
    	token = getTokenAuth(token);         
    	Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);    
    	return token;
    }
    
    public static String getTokenAuth(String token){
        if (!(StringUtils.hasText(token) && token.startsWith("Bearer "))) {
            return null;
        }
        return token.substring(7);
    }
}
