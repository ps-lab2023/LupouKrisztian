package com.example.PieseAuto.service.impl;

import com.example.PieseAuto.dto.AuthDTO;
import com.example.PieseAuto.dto.UserDetailsDTO;
import com.example.PieseAuto.dto.UserDetailsDTOadmin;
import com.example.PieseAuto.mapper.UserMapper;
import com.example.PieseAuto.model.User;
import com.example.PieseAuto.repository.UserRepository;
import com.example.PieseAuto.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findUserById(Long userID) {
        Optional<User> foundUser = userRepository.findById(userID);
        if(foundUser.isPresent()) {
            return foundUser.get();
        }
        return null;
    }

    @Override
    public User save(UserDetailsDTO userDetails) {
        User user = UserMapper.convertToUser(userDetails);
        return userRepository.save(user);
    }

    @Override
    public User saveWithRole(UserDetailsDTOadmin userDetails) {
        User user = UserMapper.convertToUserWithRole(userDetails);
        return userRepository.save(user);
    }

    @Override
    public User delete(Long userID) {
        Optional<User> foundUser = userRepository.findById(userID);
        if(foundUser.isPresent()) {
            userRepository.deleteById(userID);
            return foundUser.get();
        }
        return null;
    }

    @Override
    public User update(UserDetailsDTO userDetails, Long userID) {
        Optional<User> foundUser = userRepository.findById(userID);
        if(foundUser.isPresent()) {
            User userNew = UserMapper.convertToUser(userDetails);
            userNew.setUserID(userID);
            return userRepository.save(userNew);
        }
        return null;
    }

    @Override
    public User updateWithRole(UserDetailsDTOadmin userDetails, Long userID) {
        Optional<User> foundUser = userRepository.findById(userID);
        if(foundUser.isPresent()) {
            User userNew = UserMapper.convertToUserWithRole(userDetails);
            userNew.setUserID(userID);
            return userRepository.save(userNew);
        }
        return null;
    }

    @Override
    public User logIn(AuthDTO authDTO) {
        return userRepository.findFirstByUserNameAndPassword(authDTO.getUserName(), authDTO.getPassword());
    }
}