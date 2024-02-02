package com.techlab.weatherinfo.controller;

import com.techlab.weatherinfo.security.entity.AuthenticationRequestInfo;
import com.techlab.weatherinfo.util.JwtUtilService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class AuthorizationController
 * <p>
 * This class is used to handle the authorization related requests
 */
@RestController
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtilService jwtUtilService;

    @PostMapping("/token")
    public String getJWTToken(@RequestBody @Valid AuthenticationRequestInfo request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtUtilService.generateToken(request.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request !");
        }
    }
}
