package com.example.htpt.controller.ManagerController;

import com.example.htpt.entity.Airline;
import com.example.htpt.entity.dto.AirlineDto;
import com.example.htpt.entity.dto.AirportDto;
import com.example.htpt.entity.dto.PlaneDto;
import com.example.htpt.mapper.AirlineMapper;
import com.example.htpt.service.AirlineService;
import com.example.htpt.service.PlaneService;
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
@RequestMapping("/manager/plane")
@RequiredArgsConstructor
public class PlaneController {
    private final PlaneService planeService;
    private final AirlineService airlineService;

    @ModelAttribute("listAirline")
    public List<AirlineDto> getAirlines(){
        return airlineService.getAllAirline();
    }
    @GetMapping("")
    public String getAllPlane(Model model){
        List<PlaneDto> listPlane = planeService.getAllPlane();
        model.addAttribute("listPlane", listPlane);
        return "manager/plane/index";
    }
    @GetMapping("/{planeId}")
    public String getPlane(Model model, @PathVariable("planeId") String planeId){
        PlaneDto planeDto = planeService.getPlaneById(planeId);
        if(planeDto == null){
            model.addAttribute("messageErr", "id is not valid");
            return "404page";
        }
        model.addAttribute("planeDto", planeDto);
        return "manager/plane/detail-plane";
    }

    @GetMapping("/add-plane")
    public String createPlane(Model model){
        model.addAttribute("planeDto", new PlaneDto());
        return "manager/plane/addNew";
    }

    @PostMapping("/add-plane")
    public String createPlane(@ModelAttribute PlaneDto planeDto, BindingResult result){
        if(result.hasErrors()){
            System.out.println("has err");
        }
        Airline airline = new Airline();
        AirlineDto airlineDto = AirlineMapper.MAPPER.mapToAirlineDto(airline);
        planeDto.setAirline(airline);
        planeService.save(planeDto);
        return "redirect:/manager/plane";
    }

    @GetMapping("update/{planeId}")
    public ModelAndView update(ModelMap modelMap, @PathVariable("planeId") String planeId){
        PlaneDto planeOptional = planeService.getPlaneById(planeId);
        if(planeOptional != null){
            modelMap.addAttribute("planeDto",planeOptional);
            return new ModelAndView("manager/plane/update", modelMap);
        }
        modelMap.addAttribute("message", "Plane is not existed");
        return new ModelAndView("forward:/manager/plane", modelMap);
    }

    @PostMapping("update/{planeId}")
    public ModelAndView update(ModelMap modelMap, @Valid @ModelAttribute("planeDto") PlaneDto planeDto, BindingResult result){
       if(result.hasErrors()){
           return new ModelAndView("manager/plane");
       }
       PlaneDto planeDto1 = new PlaneDto();
       BeanUtils.copyProperties(planeDto, planeDto1);
       planeService.save(planeDto1);
       modelMap.addAttribute("message", "update thanh cong");
       return new ModelAndView("forward:/manager/plane", modelMap);
    }

    @GetMapping("delete/{planeId}")
    public String deletePlane(Model model, @PathVariable("planeId") String planeId) {
        planeService.deleteById(planeId);
        model.addAttribute("message", "Deleted plane success");
        return "redirect:/manager/plane";
    }

    @PostMapping("save")
    public String save(Model model, @Valid @ModelAttribute("planeDto") PlaneDto planeDto, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/manager/plane/update/{airlineId}";
        }
        Airline airline = new Airline();
        AirlineDto airlineDto = AirlineMapper.MAPPER.mapToAirlineDto(airline);
        planeDto.setAirline(airline);
        planeService.save(planeDto);
        model.addAttribute("message", "Update thanh cong");
        return "redirect:/manager/plane";
    }
}
