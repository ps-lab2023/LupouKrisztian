package com.example.PieseAuto.service;

import com.example.PieseAuto.dto.AuthDTO;
import com.example.PieseAuto.dto.UserDetailsDTO;
import com.example.PieseAuto.dto.UserDetailsDTOadmin;
import com.example.PieseAuto.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    List<User> getAllUsers();
    User findUserById(Long userID);
    User save(UserDetailsDTO userDetails);
    User saveWithRole(UserDetailsDTOadmin userDetails);
    User delete(Long userID);
    User update(UserDetailsDTO userDetails, Long userID);
    User updateWithRole(UserDetailsDTOadmin userDetails, Long userID);
    User logIn(AuthDTO authDTO);
}
