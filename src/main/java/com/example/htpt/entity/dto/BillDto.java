package com.example.htpt.entity.dto;

import com.example.htpt.entity.Flight;
import com.example.htpt.entity.User;
import com.example.htpt.entity.enums.TypeFlight;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
    @Id
    private String id;
    private LocalDate ngayMua;
    private double totalAmount;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private TypeFlight type;
    private String userId;
    private String username;
    private String flightId;
    @JsonIgnore
    private User user;
    @JsonIgnore
    private Flight flight;
}
