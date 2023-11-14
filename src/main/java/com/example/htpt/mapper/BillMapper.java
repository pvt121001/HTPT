package com.example.htpt.mapper;

import com.example.htpt.entity.Bill;
import com.example.htpt.entity.dto.BillDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillMapper {
    BillMapper MAPPER = Mappers.getMapper(BillMapper.class);
    @Mapping(source = "flight.id", target = "flightId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    BillDto mapToBillDto(Bill bill);

    @Mapping(source = "flightId", target = "flight.id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "username", target = "user.username")
    Bill mapToBill(BillDto billDto);
}
