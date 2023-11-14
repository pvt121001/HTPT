package com.example.htpt.controller.ManagerController;

import com.example.htpt.entity.Airports;
import com.example.htpt.entity.Plane;
import com.example.htpt.entity.dto.AirlineDto;
import com.example.htpt.entity.dto.AirportDto;
import com.example.htpt.entity.dto.FlightDto;
import com.example.htpt.entity.dto.PlaneDto;
import com.example.htpt.mapper.PlaneMapper;
import com.example.htpt.service.AirlineService;
import com.example.htpt.service.AirportService;
import com.example.htpt.service.FlightService;
import com.example.htpt.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/manager/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    private final PlaneService planeService;
    private final AirportService airportService;
    private final AirlineService airlineService;
    @ModelAttribute("listPlane")
    public List<PlaneDto> getAllPlane(){
        return planeService.getAllPlane();
    }

    @ModelAttribute("listAirline")
    public List<AirlineDto> getAllAirline(){
        return airlineService.getAllAirline();
    }
    @ModelAttribute("listAirport")
    public List<AirportDto> getAllAirport(){
        return airportService.getAllAirport();
    }
    @GetMapping("")
    public String getAllFlight(Model model) {
        List<FlightDto> list = flightService.getAllFlight();
        model.addAttribute("listFlight", list);
        return "manager/flight/index";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("flightDto", new FlightDto());
        return "manager/flight/addNew";
    }
    @GetMapping("/update/{flightId}")
    public ModelAndView update(ModelMap modelMap, @PathVariable("flightId") String flightId){
        FlightDto flightDto =  flightService.getFlightById(flightId);
        if(flightDto != null){
            modelMap.addAttribute("flightDto", flightDto);
            return new ModelAndView("manager/flight/update", modelMap);
        }
        modelMap.addAttribute("message", "Flight is not existed");
        return new ModelAndView("forward:/manager/flight/");
    }

    @PostMapping("save")
    public String save(Model model, @Valid @ModelAttribute("flightDto") FlightDto flightDto, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/manager/flight";
        }
        Plane plane = new Plane();
        PlaneDto planeDto = PlaneMapper.MAPPER.mapToPlanDto(plane);
        Airports airports = new Airports();
        flightDto.setPlane(plane);
        flightService.save(flightDto);
        model.addAttribute("message", "Thanh cong");
        return "redirect:/manager/flight";
    }
    @GetMapping("delete/{flightId}")
    public String delete(Model model, @PathVariable("flightId") String flightId) {
        flightService.deleteById(flightId);
        model.addAttribute("message", "Deleted flight success");
        return "redirect:/manager/flight";
    }
}
