package com.example.PieseAuto.service;

import com.example.PieseAuto.dto.CartDetailsDTO;
import com.example.PieseAuto.model.Cart;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CartService {
    List<CartDetailsDTO> getAllOrders();
    List<CartDetailsDTO> viewUserOrders(Long userID);
    CartDetailsDTO findOrderById(Long cartID);
    CartDetailsDTO save(Long userID);
    CartDetailsDTO deleteCart(Long cartID, Boolean admin);
    CartDetailsDTO updateStatus(Long cartID, String status);
}
