package com.blooddonation.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import jakarta.servlet.http.HttpServletResponse;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String secretKey;

    public JwtAuthenticationFilter() {
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Check for the presence of JWT token in the request header (Authorization header)
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            // Remove "Bearer " prefix and validate the token (here you can use a JWT utility class)
            String jwt = token.substring(7);
            // Add logic to validate JWT and authenticate the user here

            // Set user authentication in the SecurityContext if token is valid
            Authentication authentication = null;
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);  // Continue the filter chain
    }
}

