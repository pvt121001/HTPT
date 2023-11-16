package com.example.htpt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

// sân bay
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airports {
    @Id
    private String id;
    private String name;
    private String address;
    @OneToMany(mappedBy = "airports")
    private List<Plane> planeList;
}
