package com.example.htpt.controller.ManagerController;

import com.example.htpt.entity.dto.AirlineDto;
import com.example.htpt.entity.dto.AirportDto;
import com.example.htpt.entity.dto.PlaneDto;
import com.example.htpt.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/manager/airport")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;
    @GetMapping("")
    public String getAllAirport(Model model){
        List<AirportDto> listAirportDtos = airportService.getAllAirport();
        model.addAttribute("listAirportDtos", listAirportDtos);
        model.addAttribute("airportDto", new AirportDto());
        return "manager/airport/index";
    }
    @GetMapping("/{airportId}")
    public String getPlane(Model model, @PathVariable("airportId") String airportId){
        AirportDto airportDto = airportService.getAirportById(airportId);
        if(airportDto == null){
            model.addAttribute("messageErr", "id is not valid");
            return "404page";
        }
        model.addAttribute("airportDto", airportDto);
        return "manager/airport/detail-airport";
    }

    @GetMapping("/add-airport")
    public String createPlane(Model model){
        model.addAttribute("airportDto", new AirportDto());
        return "manager/airport/create-airport";
    }

    @PostMapping("/add-airport")
    public String createPlane(@ModelAttribute AirportDto airportDto, BindingResult result){
        if(result.hasErrors()){
            System.out.println("has err");
        }
        airportService.save(airportDto);
        return "redirect:/manager/airport";
    }

    @GetMapping("update/{airportId}")
    public ModelAndView update(ModelMap modelMap, @PathVariable("airportId") String airportId){
        AirportDto airportDto = airportService.getAirportById(airportId);
        if(airportDto != null){
            modelMap.addAttribute("airportDto",airportDto);
            return new ModelAndView("manager/airport/update", modelMap);
        }
        modelMap.addAttribute("message", "airport is not existed");
        return new ModelAndView("forward:/manager/airport", modelMap);
    }

    @PostMapping("update/{airportId}")
    public ModelAndView update(ModelMap modelMap, @Valid @ModelAttribute("airportDto") AirportDto airportDto, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("manager/airport");
        }
        AirportDto airportDto1 = new AirportDto();
        BeanUtils.copyProperties(airportDto, airportDto1);
        airportService.save(airportDto1);
        modelMap.addAttribute("message", "update thanh cong");
        return new ModelAndView("forward:/manager/airport", modelMap);
    }

    @GetMapping("delete/{airportId}")
    public String delete(Model model, @PathVariable("airportId") String airportId) {
        airportService.deleteById(airportId);
        model.addAttribute("message", "Deleted airport success");
        return "redirect:/manager/airport";
    }

    @PostMapping("save")
    public String save(Model model, @Valid @ModelAttribute("airportDto") AirportDto airportDto, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/manager/airport/update/{airportDto}";
        }
        airportService.save(airportDto);
        model.addAttribute("message", "Update thanh cong");
        return "redirect:/manager/airport";
    }
}
