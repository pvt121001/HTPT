package com.example.htpt.repository;

import com.example.htpt.entity.Flight;
import com.example.htpt.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
//    Optional<Flight> findByName(String name);

}
