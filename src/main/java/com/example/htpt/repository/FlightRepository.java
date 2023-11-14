package com.example.htpt.repository;

import com.example.htpt.entity.Flight;
import com.example.htpt.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
//    Optional<Flight> findByName(String name);
@Query(value = "SELECT * FROM Flight " +
        "WHERE deparatureDate like %?1% " +
        "   AND arrivalDate like %?2% " +
        "   AND departureLocation like %?3% " +
        "   AND arrivalLocation like %?4%", nativeQuery = true)
List<Flight> search(LocalDate deparatureDate, LocalDate arrivalDate, String departureLocation, String arrivalLocation);
}
