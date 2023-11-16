package com.example.htpt.entity.dto;

import com.example.htpt.entity.Airline;
import com.example.htpt.entity.Airports;
import com.example.htpt.entity.Flight;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaneDto {
    private String id;
    private String name;
    private String size;
    private int numberOfSeatType1;
    private int numberOfSeatType2;
    private int totalSeat;
    private String airlineId;
    private String airportId;
    private String des;
    private String imageAirline;
    @JsonIgnore
    private List<Flight> flightList;
    @JsonIgnore
    private Airline airline;
    @JsonIgnore
    private Airports airports;
}
