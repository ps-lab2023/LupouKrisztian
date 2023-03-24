package com.example.PieseAuto.model;

import com.example.PieseAuto.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long userID;
    private String userName;
    private String password;
    private Integer age;
    private String email;
    private String address;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Cart> orders;
}
