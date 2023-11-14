package com.example.htpt.controller.ManagerController;

import com.example.htpt.entity.Airline;
import com.example.htpt.entity.dto.AirlineDto;
import com.example.htpt.exception.StorageException;
import com.example.htpt.exception.StorageFileNotFoundException;
import com.example.htpt.repository.AirlineRepository;
import com.example.htpt.service.AirlineService;
import com.example.htpt.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/manager/airline")
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineRepository airlineRepository;
    private final AirlineService airlineService;
    private final StorageService storageService;
    @GetMapping("")
    public String getAllAirline(Model model){
        List<AirlineDto> list = airlineService.getAllAirline();
        List<Airline> list1 = airlineRepository.findAll();
        model.addAttribute("listAirline", list1);
        model.addAttribute("airlineDto", new AirlineDto());
        return "manager/airline/index";
    }

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws StorageFileNotFoundException {
        Resource file = storageService.loadAsRecource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/{airlineId}")
    public String getAirline(Model model, @PathVariable("airlineId") String airlineId){
        AirlineDto airlineDto = airlineService.getAirlineById(airlineId);
        if(airlineDto == null){
            model.addAttribute("messageErr", "id is not valid");
            return "404page";
        }
        model.addAttribute("airlineDto", airlineDto);
        return "manager/airline/detail-airline";
    }

    @GetMapping("/add-airline")
    public String createAirline(Model model){
        model.addAttribute("airlineDto", new AirlineDto());
        return "manager/airline/index";
    }

    @PostMapping("/add-airline")
    public String createAirline(@ModelAttribute("airlineDto") AirlineDto airlineDto, BindingResult result){
        if(result.hasErrors()){
            System.out.println("has err");
        }
        airlineService.save(airlineDto);
        return "redirect:/manager/airline";
    }
    @GetMapping("update/{airlineId}")
    public ModelAndView update(ModelMap modelMap, @PathVariable("airlineId") String airlineId){
        AirlineDto airlineDto = airlineService.getAirlineById(airlineId);
        if(airlineDto != null){
            modelMap.addAttribute("airlineDto",airlineDto);
            return new ModelAndView("manager/airline/update", modelMap);
        }
        modelMap.addAttribute("message", "Airline is not existed");
        return new ModelAndView("forward:/manager/airline", modelMap);
    }

    @PostMapping ("update/{airlineId}")
    public ModelAndView update(ModelMap modelMap, @Valid @ModelAttribute("airlineDto") AirlineDto airlineDto, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("manager/airline");
        }
        AirlineDto airlineDto1 = new AirlineDto();
        BeanUtils.copyProperties(airlineDto, airlineDto1);
        airlineService.save(airlineDto1);
        modelMap.addAttribute("message", "update thanh cong");
        return new ModelAndView("redirect:/manager/airline", modelMap);
    }

    @PostMapping("save")
    public String save(Model model, @Valid @ModelAttribute("airlineDto") AirlineDto airlineDto, BindingResult result) throws StorageException
    {
        if(result.hasErrors()){
            return "redirect:/manager/airline/update/{airlineId}";
        }
        Airline airline = new Airline();
        BeanUtils.copyProperties(airlineDto, airline);
        if(!airlineDto.getImageFile().isEmpty()){
            UUID uuid = UUID.randomUUID();
            String uuString = uuid.toString();

            airline.setImage(storageService.getStoredFileName(airlineDto.getImageFile(), uuString));// thực hiện các thao tác vs file được upload lên server
            storageService.store(airlineDto.getImageFile(), airline.getImage());
        }
        airlineRepository.save(airline);
//        airlineService.save(airlineDto);
        model.addAttribute("message", "Update thanh cong");
        return "redirect:/manager/airline";
    }
    @GetMapping("delete/{airlineId}")
    public String delete(Model model, @PathVariable("airlineId") String airlineId) {
        airlineService.deleteById(airlineId);
        model.addAttribute("message", "Deleted Airline success");
        return "redirect:/manager/airline";
    }

    @GetMapping("/search")
    public String search (Model model, @RequestParam("name") String name){
        List<AirlineDto> listAirline = airlineService.search(name);
        model.addAttribute("listAirline", listAirline);
        return "manager/airline/index";
    }
}
