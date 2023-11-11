package com.example.htpt.repository;

import com.example.htpt.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, String> {

    Optional<Plane> findByName(String name);
}
