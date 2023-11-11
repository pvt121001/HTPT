package com.example.htpt.entity.dto;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private LocalDate dob;
    private String gender;
    private String role;
    private String phoneNumber;
    private String address;
}
