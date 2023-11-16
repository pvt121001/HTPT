package com.example.htpt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plane {
    @Id
    private String id;
    private String name;
    private String size;
    private int numberOfSeatType1;
    private int numberOfSeatType2;
    private int totalSeat;
    private String des;
    @OneToMany(mappedBy = "plane", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Flight> flightList;
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;
    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airports airports;
}
