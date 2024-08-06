package com.aTh.Atherapy.controller;


import com.aTh.Atherapy.entity.Meeting;
import com.aTh.Atherapy.entity.User;
import com.aTh.Atherapy.repository.MeetingRepo;
import com.aTh.Atherapy.repository.UserRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/meeting")
public class UserMeetingController {


    private UserRepo userRepo;

    private MeetingRepo meetingRepo;


    public UserMeetingController(UserRepo userRepo, MeetingRepo meetingRepo) {
        this.userRepo = userRepo;
        this.meetingRepo = meetingRepo;
    }


    @PostMapping
    public User saveUserWithMeeting(@RequestBody User user) {
        return userRepo.save(user);
    }


    @GetMapping
    public List<User> findAllUsers() {
        return userRepo.findAll();
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getData")
    public List<Meeting> meetings() {
        return meetingRepo.findAll();
    }


    @GetMapping("/{userId}")
    public User findUser(@PathVariable Integer userId) {

        return userRepo.findById(userId).orElse(null);
    }

    @GetMapping("/find/{userName}")
    public List<User> findUserByName(@PathVariable String userName) {
        return userRepo.findByUserNameContaining(userName);
    }



}
