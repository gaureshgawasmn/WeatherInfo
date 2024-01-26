package com.techlab.weatherinfo.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.techlab.weatherinfo.constants.ApplicationConstants.X_CLIENT_ID;
import static com.techlab.weatherinfo.constants.ApplicationConstants.X_CLIENT_SECRET;

/**
 * The Class AuthenticationFilter.
 * <p>
 * This class is used to authenticate the request with custom headers.
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String clientId = request.getHeader(X_CLIENT_ID);
        String clientSecret = request.getHeader(X_CLIENT_SECRET);

        if (isValidCredentials(clientId, clientSecret)) {
            chain.doFilter(request, response);
        } else {
            // Get the current request URL
            String requestUrl = request.getRequestURL().toString();
            // Construct the API documentation URL dynamically
            String apiDocsUrl = requestUrl.replaceAll(request.getRequestURI(), "") + request.getContextPath() + "/v3/api-docs";
            // Prepare the error response with a clickable link
            String errorMessage = "{\"error\": \"Requested header parameters are missing/invalid. Please check the API documentation for more details.\", " +
                    "\"api_docs_url\": \"" + apiDocsUrl + "\"}";
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(errorMessage);
        }
    }

    private boolean isValidCredentials(String clientId, String clientSecret) {
        return clientId != null && clientSecret != null;
    }
}
