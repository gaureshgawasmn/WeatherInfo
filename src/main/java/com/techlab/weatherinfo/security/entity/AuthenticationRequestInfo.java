package com.techlab.weatherinfo.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The class AuthenticationRequestInfo
 * <p>
 * This class is created for the login information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestInfo {
    private String username;
    private String password;
}
