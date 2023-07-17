package com.example.arguteriaBackend.repo;

import com.example.arguteriaBackend.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepo extends JpaRepository<Bill,Integer> {
    List<Bill> getAllBills();

    List<Bill> getBillByUsername( @Param("username") String username);
}
