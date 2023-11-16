package com.example.htpt.entity;

import jakarta.persistence.*;
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
    @OneToMany(mappedBy = "airports", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Plane> planeList;
}
