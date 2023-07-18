package com.example.arguteriaBackend.controller;

import com.example.arguteriaBackend.model.Poll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequestMapping("api/v1/poll")
public interface PollControl {
    @PostMapping
    public ResponseEntity<String> generatePoll(@RequestBody Poll poll);

    @GetMapping
    public ResponseEntity<List<Poll>> getPolls();

    //for closing the poll
    @GetMapping("visible")
    public ResponseEntity<List<Poll>> getVisiblePolls();

    //endpoint for submitting the vote
    @PostMapping("{id}/vote/{optionId}")
    public ResponseEntity<String> submitVote(@PathVariable Integer id, @PathVariable Integer optionId);


}
