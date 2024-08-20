package com.aTh.Atherapy.controller;

import com.aTh.Atherapy.entity.UserProfile;
import com.aTh.Atherapy.repository.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserProfileController {


    private UserProfileRepo userProfileRepo;


    public UserProfileController(UserProfileRepo userProfileRepo) {
        this.userProfileRepo = userProfileRepo;
    }




}
