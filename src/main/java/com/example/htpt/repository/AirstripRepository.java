package com.example.htpt.repository;

import com.example.htpt.entity.Airstrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirstripRepository extends JpaRepository<Airstrip, String> {
}
