package com.example.htpt.entity.dto;

import com.example.htpt.entity.Flight;
import com.example.htpt.entity.Plane;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineDto {
    private String id;
    private String name;
    @JsonIgnore
    private List<Plane> planeList;
}
