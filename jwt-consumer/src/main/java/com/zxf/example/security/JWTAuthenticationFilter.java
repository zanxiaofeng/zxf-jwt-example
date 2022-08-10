package com.zxf.example.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.zxf.example.service.JWTTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTokenStore jwtTokenStore;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        DecodedJWT decodedJWT = jwtTokenStore.loadJWToken(request);
        if (decodedJWT != null) {
            SecurityContextHolder.getContext().setAuthentication(new JWTAuthentication(decodedJWT));
        }

        filterChain.doFilter(request, response);

        SecurityContextHolder.clearContext();
    }
}