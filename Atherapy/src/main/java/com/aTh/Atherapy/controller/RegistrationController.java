package com.aTh.Atherapy.controller;

import com.aTh.Atherapy.entity.Authority;
import com.aTh.Atherapy.entity.User;
import com.aTh.Atherapy.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityRepository authorityRepository;

    @PostMapping
    public String registerUser(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = passwordEncoder.encode(user.get("password"));
        boolean enabled = true; // Set to true for active account

        // Create the user entity
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEnabled(enabled);

        // Create the authority entity
        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        authority.setUser(newUser);

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        newUser.setAuthorities(authorities);

        // Save the user and authorities to the database
        userDetailsManager.createUser(org.springframework.security.core.userdetails.User.withUsername(username)
                .password(password)
                .authorities("ROLE_USER") // Assign the USER role
                .disabled(!enabled)
                .build());

        authorityRepository.save(authority);

        return "User registered successfully";
    }
}
