package com.example.htpt.controller.ManagerController;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager/home")
@RequiredArgsConstructor
public class HomeManagerController {
    @GetMapping("")
    public String getHomeManagerView(Model model) {
        return "manager/home";
    }
}
