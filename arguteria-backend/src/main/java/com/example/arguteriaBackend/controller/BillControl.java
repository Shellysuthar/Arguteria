package com.example.arguteriaBackend.controller;

import com.example.arguteriaBackend.model.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("api/v1/bill")
public interface BillControl{
    @GetMapping
    ResponseEntity<List<Bill>> getBill();
    @PostMapping("generateBill")
    ResponseEntity<String > generateBill(@RequestBody Map<String, Object> requestMap);


}
