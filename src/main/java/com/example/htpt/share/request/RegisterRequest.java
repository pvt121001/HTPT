package com.example.htpt.share.request;

import com.example.htpt.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private String fullName;
    private String email;
    private LocalDate dob;
    private Gender gender;
    private String phoneNumber;
    private String address;
}
