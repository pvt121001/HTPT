package com.example.htpt.service;

import com.example.htpt.entity.dto.AirportDto;
import com.example.htpt.entity.dto.PlaneDto;

import java.util.List;

public interface AirportService {
    AirportDto findByName(String name);
    void save (AirportDto airportDto);
    List<AirportDto> getAllAirport();
    AirportDto getAirportById(String id);
    void deleteById(String id);
}
