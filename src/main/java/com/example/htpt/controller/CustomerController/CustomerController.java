package com.example.htpt.controller.CustomerController;

import com.example.htpt.entity.dto.UserDto;
import com.example.htpt.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/info")
    public String getInfoView(Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            model.addAttribute("message", "Session is expired");
            return "redirect:/auth/login";
        }
        UserDto userDto = userService.findByUsername(username);
        model.addAttribute("userDto", userDto);
        return "user-info";
    }
}