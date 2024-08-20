package com.aTh.Atherapy.controller;


import com.aTh.Atherapy.entity.UserProfile;
import com.aTh.Atherapy.repository.UserProfileRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;





import java.util.Map;


@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    PasswordEncoder passwordEncoder;


   @Autowired
   UserProfileRepo userProfileRepo;





    @Autowired
    private JdbcUserDetailsManager userDetailsManager;



    @Transactional
    @PostMapping
    public String registerUser(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = passwordEncoder.encode(user.get("password"));
        boolean enabled = true;

        // Create the user entity


        UserDetails userDetails = User.builder()
                .username(username)
                .password(password)
                .authorities("ROLE_USER")
                .build();

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(username);

        userProfileRepo.save(userProfile);

        userDetailsManager.createUser(userDetails);

        return "User registered successfully";
    }



}
