package com.example.htpt.entity.dto;

import com.example.htpt.entity.Flight;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirstripDto {
    private String id;
    private String location;
    private double length;
    private double width;
    private String status;
    @JsonIgnore
    private List<Flight> flightList;
}
