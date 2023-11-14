package com.example.htpt.entity.dto;

import com.example.htpt.entity.Bill;
import com.example.htpt.entity.Plane;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private String id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate deparatureDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")

    private LocalDate arrivalDate;
    private String departureLocation;
    private String arrivalLocation;
    private String departureTime;
    private String note;
    private String planeId;
    private String image;
    private double price;
    @JsonIgnore
    private Plane plane;
    @JsonIgnore
    private List<Bill> bills;
}
