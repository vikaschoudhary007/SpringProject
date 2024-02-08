package com.vikas.ConnectSocial.config;

import com.vikas.ConnectSocial.constant.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtProvider {

    public  static SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(Authentication authentication){

        String jwt = Jwts.builder()
                .setIssuer("Connect-Social")
                .setSubject("JWT Token")
                .claim("username", authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 24*60*60*1000))
                .signWith(key)
                .compact();

        return jwt;
    }

    public static String getEmailFromJwtToken(String token){

        token = token.substring(7);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return String.valueOf(claims.get("username"));
    }
}
