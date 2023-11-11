package com.example.htpt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
//
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airstrip {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
        private String id;
        private String location;
        private double length;
        private double width;
        private String status;
}
