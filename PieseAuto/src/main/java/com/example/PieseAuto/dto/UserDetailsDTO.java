package com.example.PieseAuto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {
    private String userName;
    private String password;
    private Integer age;
    private String email;
    private String address;
    private String phoneNumber;
}
