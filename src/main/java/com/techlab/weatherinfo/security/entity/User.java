package com.techlab.weatherinfo.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The class User
 * <p>
 * This class is used to store authentication and authorization information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userName;
    private String passWord;
    private List<String> roles;
}
