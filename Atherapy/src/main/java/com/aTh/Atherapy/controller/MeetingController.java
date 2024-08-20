package com.aTh.Atherapy.controller;


import com.aTh.Atherapy.entity.Meeting;
import com.aTh.Atherapy.repository.MeetingRepo;
import com.aTh.Atherapy.repository.UserProfileRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/meetings")
public class MeetingController {

    private UserProfileRepo userProfileRepo;
    private MeetingRepo meetingRepo;

    public MeetingController(UserProfileRepo userProfileRepo, MeetingRepo meetingRepo) {
        this.userProfileRepo = userProfileRepo;
        this.meetingRepo = meetingRepo;
    }


    @PostMapping("/createmeeting")
    public Meeting createMeeting(@RequestBody Meeting meeting) {
        return meetingRepo.save(meeting);

    }

    @GetMapping("/meetinglist")
    public List<Meeting> getMeetingList() {
        return meetingRepo.findAll();
    }



}
