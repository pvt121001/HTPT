package com.example.htpt.repository;

import com.example.htpt.entity.Airports;
import com.example.htpt.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<Airports, String> {
    Optional<Airports> findByName(String name);
}
