package com.techlab.weatherinfo.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class SecurityConfig.
 * <p>
 * This class is used to configure the security.
 */
@Configuration
public class SecurityConfig {
    @Bean
    public FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        registrationBean.setFilter(authenticationFilter);
        registrationBean.addUrlPatterns("/weather/*"); // Specify URL patterns that require authentication
        return registrationBean;
    }
}
