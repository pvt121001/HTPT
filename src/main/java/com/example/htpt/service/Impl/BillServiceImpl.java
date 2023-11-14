package com.example.htpt.service.Impl;

import com.example.htpt.entity.Bill;
import com.example.htpt.entity.dto.BillDto;
import com.example.htpt.mapper.BillMapper;
import com.example.htpt.repository.BillRepository;
import com.example.htpt.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    @Override
    public void save(BillDto billDto) {
        Bill bill = BillMapper.MAPPER.mapToBill(billDto);
        billRepository.save(bill);
    }

    @Override
    public List<BillDto> getMyBooking(String id) {
        List<Bill> list = billRepository.getMyBooking(id);
        return  list.stream().map(BillMapper.MAPPER::mapToBillDto).toList();
    }
}
