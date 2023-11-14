package com.example.htpt.entity;


import com.example.htpt.entity.enums.Gender;
import com.example.htpt.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private LocalDate dob;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "user")
    private List<Bill> billList;

}
