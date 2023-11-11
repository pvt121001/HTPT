package com.example.htpt.mapper;

import com.example.htpt.entity.Airports;
import com.example.htpt.entity.dto.AirportDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AirportMapper {
    AirportMapper MAPPER = Mappers.getMapper(AirportMapper.class);
    Airports mapToAirPort(AirportDto airportDto);
    AirportDto mapToAirportDto (Airports airports);

}
