package com.example.htpt.controller;

import com.example.htpt.entity.dto.UserDto;
import com.example.htpt.share.request.ChangePasswordRequest;
import com.example.htpt.share.request.LoginRequest;
import com.example.htpt.share.request.RegisterRequest;
import com.example.htpt.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticateController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/login")
    public String getLoginView(Model model) {
        model.addAttribute("loginDto", new LoginRequest());
        return "login";
    }
    @PostMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute("loginDto") LoginRequest loginRequest, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("login");
            modelAndView.addObject("errors", result.getAllErrors());
        }
        UserDto userDto = userService.login(loginRequest);
        if (userDto == null) {
            modelAndView.setViewName("login");
            modelAndView.addObject("messageError", "Invalid username or password!");
        } else {
            modelAndView.setViewName("home");
            session.setAttribute("username", userDto.getUsername());
        }
        return modelAndView;
    }
    @GetMapping("/register")
    public String getRegisterView(Model model) {
        model.addAttribute("registerDto", new RegisterRequest());
        return "register";
    }
    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("registerDto") RegisterRequest dto, BindingResult result) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("register");
            modelAndView.addObject("errors", result.getAllErrors());
        }
        UserDto newUser = userService.register(dto);
        modelAndView.setViewName("home");
        session.setAttribute("username", newUser.getUsername());
        return modelAndView;
    }
    @GetMapping("/change-pass")
    public String getChangePassView(Model model) {
        model.addAttribute("changePassDto", new ChangePasswordRequest());
        return "change-password";
    }
    @PostMapping("/change-pass")
    public ModelAndView changePassword(@Valid @ModelAttribute("changePassDto") ChangePasswordRequest dto, BindingResult result) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("change-pass");
            modelAndView.addObject("errors", result.getAllErrors());
        }
        String username = (String) session.getAttribute("username");
        boolean isSuccess = userService.changePassword(username, dto);
        if (isSuccess) {
            modelAndView.setViewName("home");
        } else {
            modelAndView.setViewName("change-pass");
        }
        return modelAndView;
    }
}
