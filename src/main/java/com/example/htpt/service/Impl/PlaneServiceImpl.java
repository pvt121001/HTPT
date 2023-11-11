package com.example.htpt.service.Impl;

import com.example.htpt.entity.Plane;
import com.example.htpt.entity.dto.PlaneDto;
import com.example.htpt.mapper.PlaneMapper;
import com.example.htpt.repository.PlaneRepository;
import com.example.htpt.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository planeRepository;
    @Override
    public PlaneDto findByName(String name) {
        Optional<Plane> planeOptional = planeRepository.findByName(name);
        return planeOptional.map(PlaneMapper.MAPPER::mapToPlanDto).orElse(null);
    }

    @Override
    public void save(PlaneDto planeDto) {
        Plane plane = PlaneMapper.MAPPER.mapToPlane(planeDto);
        planeRepository.save(plane);
    }

    @Override
    public List<PlaneDto> getAllPlane() {
        List<Plane> list = planeRepository.findAll();
        return list.stream().map(PlaneMapper.MAPPER::mapToPlanDto).toList();
    }

    @Override
    public PlaneDto getPlaneById(String id) {
        Optional<Plane> planeOptional = planeRepository.findById(id);
        return planeOptional.map(PlaneMapper.MAPPER::mapToPlanDto).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        planeRepository.deleteById(id);
    }
}
