package com.example.PieseAuto.service.impl;

import com.example.PieseAuto.dto.CartDetailsDTO;
import com.example.PieseAuto.enums.OrderStatus;
import com.example.PieseAuto.mapper.CartMapper;
import com.example.PieseAuto.model.Cart;
import com.example.PieseAuto.model.User;
import com.example.PieseAuto.repository.CartRepository;
import com.example.PieseAuto.repository.UserRepository;
import com.example.PieseAuto.service.CartService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<CartDetailsDTO> getAllOrders() {
        return CartMapper.convertCartListToCartDetailsList((List<Cart>) cartRepository.findAll());
    }

    @Override
    public List<CartDetailsDTO> viewUserOrders(Long userID) {
        Optional<User> foundUser = userRepository.findById(userID);
        if(foundUser.isPresent()) {
            List<Cart> foundOrders = cartRepository.findAllByUser_UserID(userID);
            return CartMapper.convertCartListToCartDetailsList(foundOrders);
        }
        return null;
    }

    @Override
    public CartDetailsDTO findOrderById(Long cartID) {
        Optional<Cart> foundCart = cartRepository.findById(cartID);
        if(foundCart.isPresent()) {
            return CartMapper.convertToCartDetailsDTO(foundCart.get());
        }
        return null;
    }

    @Override
    public CartDetailsDTO save(Long userID) {
        Optional<User> foundUser = userRepository.findById(userID);
        if(foundUser.isPresent()) {
            Cart cart = CartMapper.convertToEmptyCart(foundUser.get());
            User upUser = foundUser.get();
            upUser.setUserID(userID);
            List<Cart> userOrders = upUser.getOrders();
            userOrders.add(cart);
            upUser.setOrders(userOrders);
            userRepository.save(upUser);
            cartRepository.save(cart);
            return CartMapper.convertToCartDetailsDTO(cart);
        }
        return null;
    }

    @Override
    public CartDetailsDTO deleteCart(Long cartID, Boolean admin) {
        Optional<Cart> foundCart = cartRepository.findById(cartID);
        if(foundCart.isPresent()) {
            if(admin || foundCart.get().getStatus() == OrderStatus.CART) {
                User user = foundCart.get().getUser();
                List<Cart> userOrders = user.getOrders();
                userOrders.remove(foundCart.get());
                user.setOrders(userOrders);
                userRepository.save(user);
                cartRepository.deleteById(cartID);
            }
            else
                return null;
            return CartMapper.convertToCartDetailsDTO(foundCart.get());
        }
        return null;
    }

    @Override
    public CartDetailsDTO updateStatus(Long cartID, String status) {
        Optional<Cart> foundCart = cartRepository.findById(cartID);
        if(foundCart.isPresent()) {
            Cart cart = foundCart.get();
            cart.setStatus(OrderStatus.valueOf(status));
            User user = foundCart.get().getUser();
            List<Cart> userOrders = user.getOrders();
            userOrders.remove(foundCart.get());
            userOrders.add(cart);
            user.setOrders(userOrders);
            userRepository.save(user);
            cartRepository.save(cart);
            return CartMapper.convertToCartDetailsDTO(cart);
        }
        return null;
    }
}
