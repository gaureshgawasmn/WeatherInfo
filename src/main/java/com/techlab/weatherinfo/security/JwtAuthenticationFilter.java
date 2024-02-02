package com.techlab.weatherinfo.security;

import com.techlab.weatherinfo.util.JwtUtilService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.techlab.weatherinfo.constants.ApplicationConstants.BEARER_START;

/**
 * The class JwtAuthenticationFilter
 * <p>
 * This class is used to filter the request based on JWT token
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    UserDetailsService userService;

    JwtUtilService jwtUtilService;

    @Autowired
    public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtUtilService jwtUtilService) {
        this.userService = userDetailsService;
        this.jwtUtilService = jwtUtilService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractTokenFromRequest(request);
        try {
            String username = null;
            if (token != null) {
                username = jwtUtilService.extractUsername(token);
            }
            // Validate and set authentication in SecurityContext if token is valid
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.loadUserByUsername(username);
                if (jwtUtilService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | SignatureException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"" + e.getLocalizedMessage() + "\"}");
        }
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken)) {
            if (bearerToken.startsWith(BEARER_START))
                return bearerToken.substring(7); // Remove "Bearer " prefix
            return bearerToken;
        }
        return null;
    }
}
