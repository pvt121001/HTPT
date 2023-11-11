package com.example.htpt.mapper;

import com.example.htpt.entity.Airstrip;
import com.example.htpt.entity.dto.AirstripDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AirstripMapper {
    AirstripMapper MAPPER = Mappers.getMapper(AirstripMapper.class);
    Airstrip mapToAirstrip(AirstripDto airstripDto);
    AirstripDto mapToAirstripDto(Airstrip airstrip);
}
