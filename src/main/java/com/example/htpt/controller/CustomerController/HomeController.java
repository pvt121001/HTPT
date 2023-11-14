package com.example.htpt.controller.CustomerController;

import com.example.htpt.entity.dto.AirlineDto;
import com.example.htpt.entity.dto.FlightDto;
import com.example.htpt.entity.dto.PlaneDto;
import com.example.htpt.service.AirlineService;
import com.example.htpt.service.AirportService;
import com.example.htpt.service.FlightService;
import com.example.htpt.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class HomeController {
    private final FlightService flightService;
    private final PlaneService planeService;
    private final AirlineService airlineService;
    @GetMapping("/home")
    public String getHomeView(Model model) {
        return "/customer/home";
    }
    @ModelAttribute("listPlane")
    public List<PlaneDto> getAllPlane(){
        return planeService.getAllPlane();
    }

    @ModelAttribute("listAirline")
    public List<AirlineDto> getAllAirline(){
        return airlineService.getAllAirline();
    }
    @GetMapping("/flight")
    public String getAllFlight(Model model) {
        List<FlightDto> list = flightService.getAllFlight();
        model.addAttribute("listFlight", list);
        return "/customer/flight";
    }
    @PostMapping("/search")
    public String search (ModelMap modelMap,
                          @RequestParam LocalDate deparatureDate,
                          @RequestParam LocalDate arrivalDate,
                          @RequestParam String departureLocation,
                          @RequestParam String arrivalLocation){
        List<FlightDto> list = flightService.search(deparatureDate, arrivalDate, departureLocation, arrivalLocation);
        modelMap.addAttribute("listFlight", list);
        return "customer/search";

    }
}
