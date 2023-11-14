package com.example.htpt.mapper;

import com.example.htpt.entity.Flight;
import com.example.htpt.entity.dto.FlightDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FlightMapper {
    FlightMapper MAPPER = Mappers.getMapper(FlightMapper.class);
    @Mapping(source = "planeId", target = "plane.id")
    @Mapping(source = "image", target = "plane.airline.image")
    Flight mapToFlight(FlightDto flightDto);
    @Mapping( source = "plane.id", target = "planeId")
    @Mapping(source = "plane.airline.image", target = "image")
    FlightDto mapToFlightDto(Flight flight);
}
