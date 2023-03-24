package com.example.PieseAuto.mapper;

import com.example.PieseAuto.dto.UserDetailsDTO;
import com.example.PieseAuto.dto.UserDetailsDTOadmin;
import com.example.PieseAuto.enums.Role;
import com.example.PieseAuto.model.User;

public class UserMapper {
    public static UserDetailsDTO convertToUserDetailsDTO(User user) {
        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setUserName(user.getUserName());
        dto.setPassword(user.getPassword());
        dto.setAddress(user.getAddress());
        dto.setAge(user.getAge());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }

    public static User convertToUser(UserDetailsDTO userDetails) {
        User user = new User();
        user.setUserName(userDetails.getUserName());
        user.setPassword(userDetails.getPassword());
        user.setAddress(userDetails.getAddress());
        user.setAge(userDetails.getAge());
        user.setEmail(userDetails.getEmail());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setRole(Role.CLIENT);
        return user;
    }

    public static User convertToUserWithRole(UserDetailsDTOadmin userDetails) {
        User user = new User();
        user.setUserName(userDetails.getUserName());
        user.setPassword(userDetails.getPassword());
        user.setAddress(userDetails.getAddress());
        user.setAge(userDetails.getAge());
        user.setEmail(userDetails.getEmail());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setRole(userDetails.getRole());
        return user;
    }
}
