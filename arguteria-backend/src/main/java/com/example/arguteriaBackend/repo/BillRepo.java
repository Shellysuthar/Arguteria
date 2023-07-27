package com.example.arguteriaBackend.repo;

import com.example.arguteriaBackend.model.Bill;
import com.example.arguteriaBackend.model.Poll;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepo extends JpaRepository<Bill,Integer> {
    List<Bill> findAllByEmail( String email );
    List<Bill> findAllByCompleted( boolean b);

    @Query(value = "SELECT b FROM Bill b WHERE b.email = :email AND b.completed = :completed")
    public List<Bill> findAllByEmailCompleted(@Param("email") String email, @Param("completed") boolean completed);
}
