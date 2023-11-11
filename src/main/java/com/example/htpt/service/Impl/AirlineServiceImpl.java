package com.example.htpt.service.Impl;

import com.example.htpt.entity.Airline;
import com.example.htpt.entity.Airports;
import com.example.htpt.entity.dto.AirlineDto;
import com.example.htpt.mapper.AirlineMapper;
import com.example.htpt.mapper.AirportMapper;
import com.example.htpt.repository.AirlineRepository;
import com.example.htpt.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;
    @Override
    public AirlineDto findByName(String name) {
        Optional<Airline> airline = airlineRepository.findByName(name);
        return airline.map(AirlineMapper.MAPPER::mapToAirlineDto).orElse(null);
    }

    @Override
    public void save(AirlineDto airlineDto) {
        Airline airline = AirlineMapper.MAPPER.mapToAirline(airlineDto);
        airlineRepository.save(airline);
    }

    @Override
    public List<AirlineDto> getAllAirline() {
        List<Airline> list = airlineRepository.findAll();
        return list.stream().map(AirlineMapper.MAPPER::mapToAirlineDto).toList();
    }

    @Override
    public AirlineDto getAirlineById(String id) {
        Optional<Airline> airline = airlineRepository.findById(id);
        return airline.map(AirlineMapper.MAPPER::mapToAirlineDto).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        airlineRepository.deleteById(id);
    }

    @Override
    public List<AirlineDto> search(String name) {
        List<Airline> list = airlineRepository.searchByName(name);
        return list.stream().map(AirlineMapper.MAPPER::mapToAirlineDto).toList();
    }
}
