package com.example.arguteriaBackend.controller;

import com.example.arguteriaBackend.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/api/v1/product")
public interface ProductControl {
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Map<String,String> requestMap);

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct();

    @PostMapping("/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody Map<String,String> requestMap);

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id);
}
