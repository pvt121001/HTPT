package com.example.htpt.service;

import com.example.htpt.entity.dto.UserDto;
import com.example.htpt.share.request.ChangePasswordRequest;
import com.example.htpt.share.request.LoginRequest;
import com.example.htpt.share.request.RegisterRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService{
    UserDto findByUsername(String username);
    void save(UserDto userDto);
    UserDto login (LoginRequest request);
    UserDto register (RegisterRequest request) throws Exception;
    boolean changePassword(String username, ChangePasswordRequest request) throws Exception;
    void createCustomer(UserDto userDto);
    List<UserDto> getAllCustomer();
    UserDto getCustomerByUserId(String userId);
    void deleteByUserId(String userId);
}
