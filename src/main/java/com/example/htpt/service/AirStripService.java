package com.example.htpt.service;


import com.example.htpt.entity.dto.AirstripDto;

import java.util.List;

public interface AirStripService {
    void save (AirstripDto airstripDto);

    List<AirstripDto> getAllAirstrip();
    AirstripDto getAirstripById(String id);
    void deleteById(String id);
}
