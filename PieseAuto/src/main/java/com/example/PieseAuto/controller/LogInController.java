package com.example.PieseAuto.controller;

import com.example.PieseAuto.dto.AuthDTO;
import com.example.PieseAuto.model.User;
import com.example.PieseAuto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LogInController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity logIn(@RequestBody AuthDTO auth) {
        User loggedUser = userService.logIn(auth);
        if(loggedUser != null)
            return ResponseEntity.status(HttpStatus.OK).body(loggedUser);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login failed");
    }
}
