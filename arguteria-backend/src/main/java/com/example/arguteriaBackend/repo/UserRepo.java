package com.example.arguteriaBackend.repo;

import com.example.arguteriaBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByEmailId(@Param("email") String email);
    List<User>getAllUser();
    @Override
    Optional<User> findById(Integer integer);
    List<User>getByUsername(@Param("username") String currentUser);

}
