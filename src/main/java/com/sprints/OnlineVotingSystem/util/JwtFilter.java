package com.sprints.OnlineVotingSystem.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtService jwtUtil;

    public JwtFilter(JwtService jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        boolean skip = path.startsWith("/api/auth/login") || path.startsWith("/api/users/register");

        log.debug("shouldNotFilter → path={} | skip={}", path, skip);

        return skip;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String path = request.getServletPath();
        String header = request.getHeader("Authorization");

        log.debug("Executing JwtFilter for path={}", path);

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            log.debug("Authorization header found, token={}", token);

            try {
                if (jwtUtil.isValid(token)) {
                    String username = jwtUtil.username(token);
                    String role = jwtUtil.role(token);

                    log.debug("Token is valid. Setting authentication for user={} with role={}", username, role);

                    var auth = new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            List.of(() -> role)
                    );

                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    log.warn("Invalid token received for path={}", path);
                }
            } catch (Exception ex) {
                log.error("JWT validation failed: {}", ex.getMessage(), ex);
            }
        } else {
            log.debug("No Authorization header provided for path={}", path);
        }

        chain.doFilter(request, response);
    }
}
