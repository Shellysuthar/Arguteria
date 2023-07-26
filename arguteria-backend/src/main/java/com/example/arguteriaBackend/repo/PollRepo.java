package com.example.arguteriaBackend.repo;

import java.util.List;
import java.util.Optional;

import com.example.arguteriaBackend.model.Poll;
import com.example.arguteriaBackend.model.User;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PollRepo extends JpaRepository<Poll, Integer> {

    List<Poll> findAllByOrderByEndDateDesc();
    @Query(value ="SELECT p FROM Poll p WHERE p.visible=true")
    public List<Poll> findAllByVisible(boolean b);

}