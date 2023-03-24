package com.example.PieseAuto.dto;

import com.example.PieseAuto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTOadmin {
    private String userName;
    private String password;
    private Integer age;
    private String email;
    private String address;
    private String phoneNumber;
    private Role role;
}
