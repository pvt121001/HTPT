package com.example.htpt.service;

import com.example.htpt.entity.Plane;
import com.example.htpt.entity.dto.PlaneDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PlaneService {
    PlaneDto findByName(String name);
    void save (PlaneDto planeDto);
    List<PlaneDto> getAllPlane();
    PlaneDto getPlaneById(String id);
    void deleteById(String id);

}
