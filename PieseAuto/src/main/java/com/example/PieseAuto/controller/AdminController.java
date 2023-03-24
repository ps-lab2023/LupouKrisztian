package com.example.PieseAuto.controller;

import com.example.PieseAuto.dto.CartDetailsDTO;
import com.example.PieseAuto.dto.PartDetailsDTO;
import com.example.PieseAuto.dto.UserDetailsDTOadmin;
import com.example.PieseAuto.model.Cart;
import com.example.PieseAuto.model.Part;
import com.example.PieseAuto.model.User;
import com.example.PieseAuto.service.CartService;
import com.example.PieseAuto.service.PartService;
import com.example.PieseAuto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    PartService partService;
    @Autowired
    CartService cartService;

    @GetMapping("/show-users")
    public ResponseEntity showUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/show-parts")
    public ResponseEntity showParts() { return ResponseEntity.status(HttpStatus.OK).body(partService.getAllParts()); }

    @GetMapping("/show-orders")
    public ResponseEntity showOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.getAllOrders());
    }

    @PostMapping("/add-account")
    public ResponseEntity addUser(@RequestBody UserDetailsDTOadmin user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveWithRole(user));
    }

    @PostMapping("/add-part")
    public ResponseEntity addPart(@RequestBody PartDetailsDTO part) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partService.save(part));
    }

    @GetMapping(path = "/user-by-id/{id}")
    public ResponseEntity findUserById(@PathVariable("id") Long userID) {
        User foundUser = userService.findUserById(userID);
        if(foundUser != null)
            return ResponseEntity.status(HttpStatus.OK).body(foundUser);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + userID + " was NOT FOUND.");
    }

    @GetMapping(path = "/part-by-id/{id}")
    public ResponseEntity findPartById(@PathVariable("id") Long partID) {
        Part foundPart = partService.findPartById(partID);
        if(foundPart != null)
            return ResponseEntity.status(HttpStatus.OK).body(foundPart);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Part with id " + partID + " was NOT FOUND.");
    }

    @GetMapping(path = "/cart-by-id/{id}")
    public ResponseEntity findCartById(@PathVariable("id") Long cartID) {
        CartDetailsDTO foundCart = cartService.findOrderById(cartID);
        if(foundCart != null)
            return ResponseEntity.status(HttpStatus.OK).body(foundCart);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart with id " + cartID + " was NOT FOUND.");
    }

    @GetMapping("/user-orders/{id}")
    public ResponseEntity showUserOrders(@PathVariable("id") Long userID) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.viewUserOrders(userID));
    }

    @DeleteMapping(path = "/delete-account/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long userID) {
        User deletedUser = userService.delete(userID);
        if(deletedUser != null)
            return ResponseEntity.status(HttpStatus.OK).body(deletedUser);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + userID + " was NOT FOUND.");
    }

    @DeleteMapping(path = "/delete-part/{id}")
    public ResponseEntity deletePart(@PathVariable("id") Long partID) {
        Part deletedPart = partService.delete(partID);
        if(deletedPart != null)
            return ResponseEntity.status(HttpStatus.OK).body(deletedPart);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Part with id " + partID + " was NOT FOUND.");
    }

    @DeleteMapping("/delete-cart/{id}")
    public ResponseEntity deleteCart(@PathVariable("id") Long cartID) {
        CartDetailsDTO deletedCart = cartService.deleteCart(cartID, true);
        if(deletedCart != null)
            return ResponseEntity.status(HttpStatus.OK).body(deletedCart);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart with id " + cartID + " was NOT FOUND.");
    }

    @PutMapping(path = "/update-account/{id}")
    public ResponseEntity updateUserWithRole(@RequestBody UserDetailsDTOadmin user, @PathVariable("id") Long userID) {
        User updatedUser = userService.updateWithRole(user, userID);
        if(updatedUser != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id " + userID + " was NOT FOUND.");
    }

    @PutMapping(path = "/update-part/{id}")
    public ResponseEntity updatePart(@RequestBody PartDetailsDTO part, @PathVariable("id") Long partID) {
        Part updatedPart = partService.update(part, partID);
        if(updatedPart != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedPart);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Part with id " + partID + " was NOT FOUND.");
    }

    @PutMapping(path = "/update-satus/{orderID}/{status}")
    public ResponseEntity updateStatus(@PathVariable("orderID") Long orderID, @PathVariable("status") String orderStatus) {
        CartDetailsDTO updatedCart = cartService.updateStatus(orderID, orderStatus);
        if(updatedCart != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedCart);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart with id " + orderID + " was NOT FOUND.");
    }
}
