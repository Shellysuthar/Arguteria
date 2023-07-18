package com.example.arguteriaBackend.controllerImpl;

import com.example.arguteriaBackend.controller.PollControl;
import com.example.arguteriaBackend.model.Poll;
import com.example.arguteriaBackend.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class PollControlImpl implements PollControl {
    @Autowired
    PollService pollService;
    @Override
    public ResponseEntity<String> generatePoll(Poll poll) {
        try{
            return  pollService.generatePoll(poll);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Poll>> getPolls() {
        try{
            return pollService.getPolls();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Poll>> getVisiblePolls() {
        try{
            return pollService.getVisiblePolls();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> submitVote(@PathVariable Integer id, @PathVariable Integer optionId) {
        try{
           return pollService.vote(id, optionId);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
