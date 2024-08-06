package com.aTh.Atherapy.controller;


import com.aTh.Atherapy.entity.Meeting;
import com.aTh.Atherapy.entity.Request;
import com.aTh.Atherapy.entity.User;
import com.aTh.Atherapy.repository.MeetingRepo;
import com.aTh.Atherapy.repository.RequestRepo;
import com.aTh.Atherapy.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MeetingRepo meetingRepo;

    @Autowired
    private RequestRepo requestRepo;

    @PostMapping("/createRequest")
    public ResponseEntity<String> addRequest(@RequestParam Integer userId, @RequestParam Integer meetingId) {
        User user = userRepo.findById(userId).orElse(null);
        Meeting meeting = meetingRepo.findById(meetingId).orElse(null);

        if(user == null && meeting == null) {
            return ResponseEntity.status(404).body("user or Meeting not found");
        }

        Request request = new Request();
        request.setUser(user);
        request.setMeeting(meeting);
        requestRepo.save(request);

        return ResponseEntity.status(200).body("Request Added Successfully");
    }


    @DeleteMapping("/remove")
    public ResponseEntity<String> removeRequest(@RequestParam Integer requestId) {
        Request request = requestRepo.findById(requestId).orElse(null);

        if (request == null) {
            return ResponseEntity.status(404).body("Request not found");
        }

        requestRepo.delete(request);

        return ResponseEntity.ok("Request removed successfully");
    }

    @Transactional
    @DeleteMapping("/accept")
    public ResponseEntity<String> acceptRequest(@RequestParam Integer requestId) {
        Request request = requestRepo.findById(requestId).orElse(null);

        if(request == null) {
            return ResponseEntity.status(400).body("Request Not Found");
        }

        User user = request.getUser();
        Meeting meeting = request.getMeeting();

        if(user == null || meeting == null) {
            return ResponseEntity.status(400).body("user or meeting not found");
        }

        meeting.getUsers().add(user);
        user.getMeetings().add(meeting);
        requestRepo.delete(request);


        meetingRepo.save(meeting);
        userRepo.save(user);


        return ResponseEntity.status(200).body("Added " + user.getUserName() + " to "  + meeting.getTitle());
    }


    @GetMapping("/getRequests/{meetingId}")
    public List<Request> getRequests(@RequestParam Integer meetingId) {

        Meeting meeting = meetingRepo.findById(meetingId).orElse(null);

        if(meeting != null) {
            return meeting.getRequests();
        }
        return null;
    }

    @GetMapping("/getAllRequests")
    public List<Request> getAllRequests() {
        return requestRepo.findAll();
    }
}
