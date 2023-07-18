package com.example.arguteriaBackend.controller;

import com.example.arguteriaBackend.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1")
public interface UserControl {

    @PostMapping("/auth/register")
    public ResponseEntity<String> signup(@RequestBody Map<String,String> requestMap);
    @PostMapping("/auth/authenticate")
    public ResponseEntity<String> login (@RequestBody Map<String,String> requestMap);

    @GetMapping("/userDetails")
    public ResponseEntity<?> getAllUsers();
}
