package com.example.arguteriaBackend.repo;

import com.example.arguteriaBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {}

