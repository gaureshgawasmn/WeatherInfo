package com.techlab.weatherinfo.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
