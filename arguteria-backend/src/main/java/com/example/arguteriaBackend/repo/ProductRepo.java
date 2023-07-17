package com.example.arguteriaBackend.repo;

import com.example.arguteriaBackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {
List<Product> getAllProducts();

}
