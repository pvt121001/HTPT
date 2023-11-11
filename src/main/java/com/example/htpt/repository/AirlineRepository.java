package com.example.htpt.repository;

import com.example.htpt.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, String> {
    Optional<Airline> findByName(String name);
    @Query(value = "select * from airline b where b.name like '%?1%'", nativeQuery = true)
    List<Airline> searchByName(String name);
}
