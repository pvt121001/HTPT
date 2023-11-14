package com.example.htpt.entity;

import com.example.htpt.entity.enums.TypeFlight;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    private String id;
    private LocalDate ngayMua;
    private double totalAmount;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private TypeFlight type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
