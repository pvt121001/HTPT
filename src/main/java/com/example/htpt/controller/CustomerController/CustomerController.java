package com.example.htpt.controller.CustomerController;

import com.example.htpt.entity.Bill;
import com.example.htpt.entity.dto.BillDto;
import com.example.htpt.entity.dto.UserDto;
import com.example.htpt.repository.UserRepository;
import com.example.htpt.service.BillService;
import com.example.htpt.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final UserService userService;
    private final HttpSession session;
    private final BillService billService;
    private final UserRepository userRepository;

    @GetMapping("/info")
    public String getInfoView(Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            model.addAttribute("message", "Session is expired");
            return "redirect:/auth/login";
        }
        UserDto userDto = userService.findByUsername(username);
        model.addAttribute("userDto", userDto);
        return "customer/info";
    }

    @GetMapping("/booking")
    public String getMyBooking(Model model){
        String username = (String) session.getAttribute("username");
        List<BillDto> listBill = billService.getMyBooking(userRepository.findByUsername(username).get().getId());
        model.addAttribute("listBill", listBill);
        return "customer/booking";
    }

}