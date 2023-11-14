package com.example.htpt.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    private String id;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate deparatureDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate arrivalDate;
    private String departureLocation;
    private String arrivalLocation;
    private String departureTime;
    private String note;
    private double price;
    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;
    @OneToMany(mappedBy = "flight")
    private List<Bill> bills;
}
