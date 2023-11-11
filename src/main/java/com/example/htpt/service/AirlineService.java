package com.example.htpt.service;

import com.example.htpt.entity.dto.AirlineDto;
import com.example.htpt.entity.dto.AirportDto;
import com.example.htpt.entity.dto.PlaneDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AirlineService {
    AirlineDto findByName(String name);
    void save (AirlineDto airlineDto);
    List<AirlineDto> getAllAirline();
    AirlineDto getAirlineById(String id);
    void deleteById(String id);

    List<AirlineDto> search(String name);

}
