package com.zxf.example.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<HttpSession> session = Optional.ofNullable(request.getSession(false));
        session.map(ss -> ss.getAttribute("verifiedJwt"))
                .ifPresent((verifiedJWT) -> {
                    SecurityContextHolder.getContext().setAuthentication(new JWTAuthentication((DecodedJWT) verifiedJWT));
                });


        filterChain.doFilter(request, response);
    }
}