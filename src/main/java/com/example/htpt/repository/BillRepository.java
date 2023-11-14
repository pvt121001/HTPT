package com.example.htpt.repository;

import com.example.htpt.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
    @Query(value = "select * from bill where user_id = ?1", nativeQuery = true)
    List<Bill> getMyBooking(String username);
}
