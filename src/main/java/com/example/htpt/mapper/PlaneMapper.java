package com.example.htpt.mapper;

import com.example.htpt.entity.Plane;
import com.example.htpt.entity.dto.PlaneDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlaneMapper {
    PlaneMapper MAPPER = Mappers.getMapper(PlaneMapper.class);
    @Mapping(source = "airlineId", target = "airline.id")
    @Mapping(source = "imageAirline", target = "airline.image")
    @Mapping(source = "airportId", target = "airports.id")
    Plane mapToPlane(PlaneDto planeDto);
    @Mapping(source = "airline.id", target = "airlineId")
    @Mapping(source = "airline.image", target = "imageAirline")
    @Mapping(source = "airports.id", target = "airportId")
    PlaneDto mapToPlanDto(Plane plane);
}
