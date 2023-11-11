package com.example.htpt.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @OneToMany(mappedBy ="airline", cascade = CascadeType.ALL)
    private List<Plane> planeList;
}
