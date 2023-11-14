package com.example.htpt.service;

import com.example.htpt.entity.Bill;
import com.example.htpt.entity.dto.BillDto;

import java.util.List;

public interface BillService {
    void save(BillDto billDto);
    List<BillDto> getMyBooking(String id);
}
