package com.example.PieseAuto.controller;

import com.example.PieseAuto.dto.CartDetailsDTO;
import com.example.PieseAuto.dto.UserDetailsDTO;
import com.example.PieseAuto.model.Cart;
import com.example.PieseAuto.model.User;
import com.example.PieseAuto.service.CartService;
import com.example.PieseAuto.service.PartService;
import com.example.PieseAuto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PartService partService;
    @Autowired
    CartService cartService;

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody UserDetailsDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @GetMapping("/show-parts")
    public ResponseEntity showParts() { return ResponseEntity.status(HttpStatus.OK).body(partService.getAllParts()); }

    @PostMapping("/{id}/new-cart")
    public ResponseEntity newOrder(@PathVariable("id") Long userID) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.save(userID));
    }

    @GetMapping("/{id}/your-orders")
    public ResponseEntity showOrders(@PathVariable("id") Long userID) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.viewUserOrders(userID));
    }

    @DeleteMapping("/delete-cart/{id}")
    public ResponseEntity deleteCart(@PathVariable("id") Long cartID) {
        CartDetailsDTO deletedCart = cartService.deleteCart(cartID, false);
        if(deletedCart != null)
            return ResponseEntity.status(HttpStatus.OK).body(deletedCart);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart with id " + cartID + " was NOT FOUND.");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateUser(@RequestBody UserDetailsDTO user, @PathVariable("id") Long userID) {
        User updatedUser = userService.update(user, userID);
        if(updatedUser != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + userID + " was NOT FOUND.");
    }
}