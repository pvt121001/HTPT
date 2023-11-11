package com.example.htpt.controller;

import com.example.htpt.entity.dto.UserDto;
import com.example.htpt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee/customer")
@RequiredArgsConstructor
public class EmployeeController {
    private final UserService userService;

    @GetMapping("")
    public String getAllCustomer(Model model){
        List<UserDto> list = userService.getAllCustomer();
        model.addAttribute("listCustomer", list);
        return "employee/list-customer";
    }

    @GetMapping("/{userId}")
    public String getCustomer(Model model, @PathVariable("userId") String userId) {
        UserDto customer = userService.getCustomerByUserId(userId);
        if (customer == null) {
            model.addAttribute("messageError", "Id isn't valid.");
            return "404page";
        }
        model.addAttribute("customer", customer);
        return "employee/detail-customer";
    }
    @GetMapping("/add-customer")
    public String createCustomerView(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "employee/create-customer";
    }
    @PostMapping( "/add-customer")
    public String createCustomer(@ModelAttribute UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("has err");
        }
        userService.createCustomer(userDto);
        return "redirect:/employee/customer";
    }
    @DeleteMapping("/{userId}")
    public String deleteCustomer(Model model, @PathVariable("userId") String userId) {
        userService.deleteByUserId(userId);
        model.addAttribute("message", "Deleted customer success");
        return "employee/list-customer";
    }
}
