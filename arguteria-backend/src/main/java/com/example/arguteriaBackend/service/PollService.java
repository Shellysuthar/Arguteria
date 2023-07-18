package com.example.arguteriaBackend.service;

import com.example.arguteriaBackend.model.Poll;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PollService {
    ResponseEntity<String> generatePoll(Poll poll);

    ResponseEntity<List<Poll>> getPolls();

    ResponseEntity<List<Poll>> getVisiblePolls();

    ResponseEntity<String> vote(Integer id, Integer optionId);
}
