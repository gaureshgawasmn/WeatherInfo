package com.techlab.weatherinfo.service;

import com.techlab.weatherinfo.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The class UserDetailsServiceImpl
 * <p>
 * This class is used to implement the UserDetailsService interface.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Map<String, User> userNameVsUser = new HashMap<>();

    static {
        userNameVsUser.put("Admin", new User("Admin", "Admin@123", Arrays.asList("ADMIN", "USER")));
        userNameVsUser.put("User", new User("User", "User@123", Arrays.asList("ADMIN")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!userNameVsUser.containsKey(username))
            throw new UsernameNotFoundException("User not found for userName: " + username);
        User user = userNameVsUser.get(username);
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUserName(), passwordEncoder.encode(user.getPassWord()), authorities);
    }
}
