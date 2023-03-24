package com.example.PieseAuto.mapper;

import com.example.PieseAuto.dto.CartDetailsDTO;
import com.example.PieseAuto.enums.OrderStatus;
import com.example.PieseAuto.model.Cart;
import com.example.PieseAuto.model.User;

import java.util.ArrayList;
import java.util.List;

public class CartMapper {
    public static Cart convertToEmptyCart(User user) {
        Cart cart = new Cart();
        cart.setTotal(0f);
        cart.setStatus(OrderStatus.CART);
        cart.setUser(user);
        return cart;
    }

    public static CartDetailsDTO convertToCartDetailsDTO(Cart cart) {
        CartDetailsDTO cartDTO = new CartDetailsDTO();
        cartDTO.setCartID(cart.getCartID());
        cartDTO.setTotal(cart.getTotal());
        cartDTO.setStatus(cart.getStatus());
        cartDTO.setUserID(cart.getUser().getUserID());
        return cartDTO;
    }

    public static List<CartDetailsDTO> convertCartListToCartDetailsList(List<Cart> orders) {
        List<CartDetailsDTO> cartDetailsDTOList = new ArrayList<>();
        for(Cart cart : orders) {
            CartDetailsDTO cartDTO = new CartDetailsDTO();
            cartDTO.setCartID(cart.getCartID());
            cartDTO.setTotal(cart.getTotal());
            cartDTO.setStatus(cart.getStatus());
            cartDTO.setUserID(cart.getUser().getUserID());
            cartDetailsDTOList.add(cartDTO);
        }
        return cartDetailsDTOList;
    }
}
