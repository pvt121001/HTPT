package com.example.htpt.service;

import com.example.htpt.entity.dto.FlightDto;
import com.example.htpt.entity.dto.PlaneDto;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
//    FlightDto findByName(String name);
    void save (FlightDto flightDto);
    List<FlightDto> getAllFlight();
    FlightDto getFlightById(String id);
    void deleteById(String id);
    List<FlightDto> search(LocalDate deparatureDate, LocalDate arrivalDate, String departureLocation, String arrivalLocation);
}
