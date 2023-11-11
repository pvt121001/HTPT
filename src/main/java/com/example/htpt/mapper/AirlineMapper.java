package com.example.htpt.mapper;

import com.example.htpt.entity.Airline;
import com.example.htpt.entity.Airports;
import com.example.htpt.entity.dto.AirlineDto;
import com.example.htpt.entity.dto.AirportDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface AirlineMapper {
    AirlineMapper MAPPER = Mappers.getMapper(AirlineMapper.class);
    Airline mapToAirline(AirlineDto airlineDto);
    AirlineDto mapToAirlineDto (Airline airline);
}
