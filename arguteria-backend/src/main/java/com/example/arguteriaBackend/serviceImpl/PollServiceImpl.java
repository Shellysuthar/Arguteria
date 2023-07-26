package com.example.arguteriaBackend.serviceImpl;

import com.example.arguteriaBackend.error.Cafeutils;
import com.example.arguteriaBackend.jwt.JwtFilter;
import com.example.arguteriaBackend.model.Option;
import com.example.arguteriaBackend.model.Poll;
import com.example.arguteriaBackend.model.User;
import com.example.arguteriaBackend.repo.BillRepo;
import com.example.arguteriaBackend.repo.OptionRepo;
import com.example.arguteriaBackend.repo.PollRepo;
import com.example.arguteriaBackend.repo.UserRepo;
import com.example.arguteriaBackend.service.PollService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Slf4j
public class PollServiceImpl implements PollService {
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private PollRepo pollRepo;
    @Autowired
    private OptionRepo optionRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> generatePoll(Poll poll) {
        try {
            Poll savedPoll = pollRepo.save(poll);
            List<Option> options = poll.getOptions();
            if (options != null) {
                options.forEach(option -> {
                    option.setPoll(savedPoll);
                    optionRepo.save(option);
                });
            }
            return new ResponseEntity<>("Created Poll", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<List<Poll>> getPolls() {
        return new ResponseEntity<>(pollRepo.findAllByOrderByEndDateDesc(), HttpStatus.OK);
    }

    public ResponseEntity<List<Poll>> getVisiblePolls() {
        return new ResponseEntity<>(pollRepo.findAllByVisible(true), HttpStatus.OK);
    }
    @Transactional
    public ResponseEntity<String> vote(Integer id, Integer optionId) {
    Optional<Poll> poll = pollRepo.findById(id);
    //check end-date
    if ( poll.isPresent() && poll.get().getEndDate().isBefore(LocalDateTime.now())) {
        return new ResponseEntity<>("The Poll has expired", HttpStatus.OK);
    }
    System.out.println(poll.isPresent());

    User user = userRepo.findByEmail(jwtFilter.getCurrentUser());
    String userId = Integer.toString(user.getId());
    List<String> votedUsers = poll.get().getVotedUsers();
    //check the user array
    if (votedUsers.contains(userId)) {
        return new ResponseEntity<>("You have already voted", HttpStatus.OK);
    }
    Optional<Option> option = optionRepo.findById(optionId);
    if( option.isPresent()) {
        log.info("Inside vote{} ", option);
        option.get().setScore(option.get().getScore() + 1);
        optionRepo.save(option.get());
        poll.get().getVotedUsers().add(userId);
        pollRepo.save(poll.get());
        return new ResponseEntity<>("Vote Submitted!", HttpStatus.OK);
    }
//    List<Option> options = poll.get().getOptions().stream().filter(
//                            option -> Objects.equals(option.getId(), optionId)).collect(Collectors.toList());
//    if(options.size() == 1) {
//    Option option = options.get(0);
//    log.info("Inside vote{} ", options.get(0));
//    option.setScore(option.getScore() + 1);
//    optionRepo.save(option);
//    poll.get().getVotedUsers().add(userId);
//    pollRepo.save(poll.get());
//    return new ResponseEntity<>("Vote Submitted!", HttpStatus.OK);
//    }
    else {
        return new ResponseEntity<>("Option id for poll not unique!", HttpStatus.OK);
    }
    }

}



//    public Poll getPollById(Long id) {
//        return pollRepository.getOne(id);
//    }
//
//    public void deletePollById(Long id) {
//        pollRepository.deleteById(id);
//    }
//

//
//    public List<Poll> getAllForUser(String username) {
//        User user = userRepository.findOneByUsername(username);
//        return pollRepository.findAllByUser(user);
//    }
//
//    public List<Poll> getAllVisibleForUser(String username) {
//        User user = userRepository.findOneByUsername(username);
//
//        return pollRepository.findAllByUserAndVisible(user, true);
//    }