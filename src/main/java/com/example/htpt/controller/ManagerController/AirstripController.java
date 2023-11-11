package com.example.htpt.controller.ManagerController;


import com.example.htpt.entity.dto.AirstripDto;
import com.example.htpt.service.AirStripService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/manager/airstrip")
@RequiredArgsConstructor
public class AirstripController {
    private final AirStripService airStripService;
    @GetMapping("")
    public String getAllAirstrip(Model model){
//        List<AirlineDto> list = airlineService.getAllAirline();
//        model.addAttribute("listAirline", list);
        return "manager/airstrip/index";
    }
    @GetMapping("/{airstripId}")
    public String getAirstrip(Model model, @PathVariable("airstripId") String airstripId){
        AirstripDto airstripDto = airStripService.getAirstripById(airstripId);
        if(airstripDto == null){
            model.addAttribute("messageErr", "id is not valid");
            return "404page";
        }
        model.addAttribute("airstripDto", airstripDto);
        return "manager/airstrip/detail-airstrip";
    }

    @GetMapping("/add-airstrip")
    public String createAirline(Model model){
        model.addAttribute("airstripDto", new AirstripDto() {
        });
        return "manager/airstrip/create-airstrip";
    }

    @PostMapping("/add-airstrip")
    public String createAirline(@ModelAttribute AirstripDto airstripDto, BindingResult result){
        if(result.hasErrors()){
            System.out.println("has err");
        }
        airStripService.save(airstripDto);
        return "redirect:/manager/airstrip";
    }

    @GetMapping("update/{airtripId}")
    public ModelAndView update(ModelMap modelMap, @PathVariable("airtripId") String airtripId){
        AirstripDto airstripDto = airStripService.getAirstripById(airtripId);
        if(airstripDto != null){
            modelMap.addAttribute("airstripDto",airstripDto);
            return new ModelAndView("manager/airstrip/update", modelMap);
        }
        modelMap.addAttribute("message", "Airstrip is not existed");
        return new ModelAndView("forward:/manager/airlstrip", modelMap);
    }

    @PutMapping ("update/{airtripId}")
    public ModelAndView update(ModelMap modelMap, @Valid @ModelAttribute("airstripDto") AirstripDto airstripDto, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("manager/airline");
        }
        AirstripDto airstripDto1 = new AirstripDto();
        BeanUtils.copyProperties(airstripDto, airstripDto1);
        airStripService.save(airstripDto1);
        modelMap.addAttribute("message", "update thanh cong");
        return new ModelAndView("forward:/manager/airstrip", modelMap);
    }

    @DeleteMapping("/{airstripId}")
    public String deletePlane(Model model, @PathVariable("airstripId") String airstripId) {
        airStripService.deleteById(airstripId);
        model.addAttribute("message", "Deleted airstrip success");
        return "manager/airstrip";
    }
}
