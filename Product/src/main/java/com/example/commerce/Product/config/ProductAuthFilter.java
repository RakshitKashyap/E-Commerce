package com.example.commerce.Product.config;

import com.example.commerce.Product.model.DTO.Response.UserAuthDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ProductAuthFilter extends OncePerRequestFilter {

    private final UserAuthClient userAuthClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String path = request.getServletPath();

            if (isPublicPath(path)) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = request.getHeader("Authorization");
            UserAuthDto user = userAuthClient.validateAndGetUser(token);

            if (user == null || user.getEmailId() == null || user.getEmailId().isBlank()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired authorization token.");
                return;
            }

            CurrentUserContext.set(user);
            filterChain.doFilter(request, response);

        } finally {
            CurrentUserContext.clear();
        }

    }

    private boolean isPublicPath(String path) {
        return path.startsWith("/actuator")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs");
    }
}
