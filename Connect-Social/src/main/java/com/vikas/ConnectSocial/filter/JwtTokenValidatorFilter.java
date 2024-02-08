package com.vikas.ConnectSocial.filter;

import com.vikas.ConnectSocial.config.JwtProvider;
import com.vikas.ConnectSocial.constant.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JwtTokenValidatorFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(SecurityConstants.JWT_HEADER);

        if(jwt != null){
            try{
//                SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
//
//                Claims claims = Jwts.parserBuilder()
//                        .setSigningKey(key)
//                        .build()
//                        .parseClaimsJws(jwt)
//                        .getBody();
//
//                String username = String.valueOf(claims.get("username"));

                String username = JwtProvider.getEmailFromJwtToken(jwt);

                List<GrantedAuthority> authorities = new ArrayList<>();

                Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }catch (Exception e){
                throw new BadCredentialsException("Invalid Token Received");
            }
        }

        filterChain.doFilter(request, response);

    }
}
