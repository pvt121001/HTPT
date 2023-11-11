package com.example.htpt.service.Impl;

import com.example.htpt.entity.Airstrip;
import com.example.htpt.entity.dto.AirstripDto;
import com.example.htpt.mapper.AirstripMapper;
import com.example.htpt.repository.AirlineRepository;
import com.example.htpt.repository.AirstripRepository;
import com.example.htpt.service.AirStripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirStripServiceImpl implements AirStripService {

    private final AirstripRepository airstripRepository;

    @Override
    public void save(AirstripDto airstripDto) {
        Airstrip airstrip = AirstripMapper.MAPPER.mapToAirstrip(airstripDto);
        airstripRepository.save(airstrip);
    }

    @Override
    public List<AirstripDto> getAllAirstrip() {
        List<Airstrip> list = airstripRepository.findAll();
        return list.stream().map(AirstripMapper.MAPPER::mapToAirstripDto).toList();
    }

    @Override
    public AirstripDto getAirstripById(String id) {
        Optional<Airstrip> airstrip = airstripRepository.findById(id);
        return airstrip.map(AirstripMapper.MAPPER::mapToAirstripDto).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        airstripRepository.deleteById(id);
    }
}
