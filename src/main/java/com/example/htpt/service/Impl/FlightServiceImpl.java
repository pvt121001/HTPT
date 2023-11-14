package com.example.htpt.service.Impl;

import com.example.htpt.entity.Flight;
import com.example.htpt.entity.dto.FlightDto;
import com.example.htpt.mapper.FlightMapper;
import com.example.htpt.repository.FlightRepository;
import com.example.htpt.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
//
//    @Override
//    public FlightDto findByName(String name) {
//        Optional<Flight> flight = flightRepository.findByName(name);
//        return flight.map(FlightMapper.MAPPER::mapToFlightDto).orElse(null);
//    }

    @Override
    public void save(FlightDto flightDto) {
        Flight flight = FlightMapper.MAPPER.mapToFlight(flightDto);
        flightRepository.save(flight);
    }

    @Override
    public List<FlightDto> getAllFlight() {
        List<Flight> list = flightRepository.findAll();
        return list.stream().map(FlightMapper.MAPPER::mapToFlightDto).toList();
    }

    @Override
    public FlightDto getFlightById(String id) {
        Optional<Flight> flight = flightRepository.findById(id);
        return flight.map(FlightMapper.MAPPER::mapToFlightDto).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<FlightDto> search(LocalDate deparatureDate, LocalDate arrivalDate, String departureLocation, String arrivalLocation) {
        List<Flight> list = flightRepository.search(deparatureDate, arrivalDate, departureLocation, arrivalLocation);
        return list.stream().map(FlightMapper.MAPPER::mapToFlightDto).toList();
    }
}
