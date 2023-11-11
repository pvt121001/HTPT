package com.example.htpt.controller.CustomerController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/home")
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("")
    public String getHomeView(Model model) {
        return "customer/home";
    }
}
