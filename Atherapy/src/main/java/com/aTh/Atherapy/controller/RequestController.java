package com.aTh.Atherapy.controller;

import com.aTh.Atherapy.entity.Meeting;
import com.aTh.Atherapy.entity.Request;
import com.aTh.Atherapy.entity.UserProfile;
import com.aTh.Atherapy.enums.RequestStatus;
import com.aTh.Atherapy.repository.MeetingRepo;
import com.aTh.Atherapy.repository.RequestRepo;
import com.aTh.Atherapy.repository.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private UserProfileRepo userRepo;

    @Autowired
    private MeetingRepo meetingRepo;

    @Autowired
    private RequestRepo requestRepo;





    @PostMapping("/joinmeeting")
    public ResponseEntity<String> joinMeeting(@RequestParam Long userId, @RequestParam Long meetingId) {
        UserProfile user = userRepo.findById(userId).get();
        Meeting meeting = meetingRepo.findById(meetingId).get();

        if(user == null || meeting == null) {
            return ResponseEntity.status(404).body("Meeting not found or joinable");
        }

        Request newRequest = new Request();
        newRequest.setUser(user);
        newRequest.setMeeting(meeting);
        newRequest.setStatus(RequestStatus.PENDING);

        return ResponseEntity.status(200).body("Join request sent");
    };



    @GetMapping("/getAllRequests")
    public List<Request> getAllRequests() {
        return requestRepo.findAll();
    }
}
