package com.example.htpt.entity.dto;

import com.example.htpt.entity.Flight;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportDto {
    private String id;
    private String name;
    private String address;
    @JsonIgnore
    private List<Flight> flightList;
}
