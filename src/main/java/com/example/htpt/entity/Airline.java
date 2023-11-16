package com.example.htpt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

// hãng hàng không
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airline {
    @Id
    private String id;
    private String name;
    private String image;
    @OneToMany(mappedBy ="airline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Plane> planeList;
}
