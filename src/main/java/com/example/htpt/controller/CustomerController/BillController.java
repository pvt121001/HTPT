package com.example.htpt.controller.CustomerController;

import com.example.htpt.entity.Flight;
import com.example.htpt.entity.User;
import com.example.htpt.entity.dto.BillDto;
import com.example.htpt.entity.dto.FlightDto;
import com.example.htpt.entity.enums.TypeFlight;
import com.example.htpt.mapper.FlightMapper;
import com.example.htpt.repository.UserRepository;
import com.example.htpt.service.BillService;
import com.example.htpt.service.FlightService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/customer/bill")
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;
    private final UserRepository userRepository;
    private final FlightService flightService;
    @GetMapping("/add/{flightId}")
    public String booking(Model model, @PathVariable("flightId") String flightId, HttpSession httpSession)
    {
        String username = (String) httpSession.getAttribute("username");
        BillDto billDto= new BillDto();
        FlightDto flightDto = flightService.getFlightById(flightId);
        Flight flight = FlightMapper.MAPPER.mapToFlight(flightDto);
        billDto.setFlightId(flight.getId());
        billDto.setUserId(userRepository.findByUsername(username).get().getId());
        billDto.setFlight(flight);
        model.addAttribute("flight", flightDto);
        model.addAttribute("bill", billDto);
        return "customer/bill";
    }

    @PostMapping("/save")
    public String save(Model model, @Valid @ModelAttribute("billDto") BillDto billDto, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/customer/bill";
        }
        billDto.setNgayMua(LocalDate.now());
        billService.save(billDto);
        model.addAttribute("message", "thanh cong");
        return "redirect:/customer/home";
    }
}
