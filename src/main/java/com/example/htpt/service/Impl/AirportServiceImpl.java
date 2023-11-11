package com.example.htpt.service.Impl;

import com.example.htpt.entity.Airports;
import com.example.htpt.entity.Plane;
import com.example.htpt.entity.dto.AirportDto;
import com.example.htpt.mapper.AirportMapper;
import com.example.htpt.mapper.PlaneMapper;
import com.example.htpt.repository.AirportRepository;
import com.example.htpt.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;
    @Override
    public AirportDto findByName(String name) {
        Optional<Airports> airport = airportRepository.findByName(name);
        return airport.map(AirportMapper.MAPPER::mapToAirportDto).orElse(null);
    }

    @Override
    public void save(AirportDto airportDto) {
        Airports airports = AirportMapper.MAPPER.mapToAirPort(airportDto);
        airportRepository.save(airports);
    }

    @Override
    public List<AirportDto> getAllAirport() {
        List<Airports> list = airportRepository.findAll();
        return list.stream().map(AirportMapper.MAPPER::mapToAirportDto).toList();
    }

    @Override
    public AirportDto getAirportById(String id) {
        Optional<Airports> airports = airportRepository.findById(id);
        return airports.map(AirportMapper.MAPPER::mapToAirportDto).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        airportRepository.deleteById(id);
    }
}
