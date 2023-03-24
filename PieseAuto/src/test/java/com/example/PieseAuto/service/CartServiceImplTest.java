package com.example.PieseAuto.service;

import com.example.PieseAuto.dto.CartDetailsDTO;
import com.example.PieseAuto.enums.OrderStatus;
import com.example.PieseAuto.enums.Role;
import com.example.PieseAuto.model.Cart;
import com.example.PieseAuto.model.User;
import com.example.PieseAuto.repository.CartRepository;
import com.example.PieseAuto.repository.UserRepository;
import com.example.PieseAuto.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceImplTest {
    @Mock
    private CartRepository cartRepository;
    @InjectMocks
    private CartServiceImpl cartService;
    @Mock
    private UserRepository userRepository;

    private Cart c1;
    private Cart c2;
    private User u1;
    private List<Cart> o1;
    private List<Cart> o2;
    List<Cart> cartList;

    @BeforeEach
    void setup() {
        cartService = new CartServiceImpl(cartRepository, userRepository);
        cartList = new ArrayList<>();
        o1 = new ArrayList<>();
        u1 = new User((long)1,"Lupul Cristian", "1234", 22, "clupi@yahoo.com",
                "Str. Mehedinti, Cluj-Napoca", "0756678543", Role.ADMIN, o1);

        c1 = new Cart((long)1, 0f, OrderStatus.CART, u1);
        c2 = new Cart((long)2, 450f, OrderStatus.COMPLETED, u1);
        o1.add(c1);
        o1.add(c2);
        u1.setOrders(o1);
    }

    @Test
    void newCart() {
        when(cartRepository.save(any(Cart.class))).thenReturn(c1);
        CartDetailsDTO savedCart = cartService.save(u1.getUserID());
        assertEquals(savedCart.getUserID(), c1.getUser().getUserID());
    }

    @Test
    void getAllCarts() {
        cartList.add(c1);
        cartList.add(c2);

        when(cartRepository.findAll()).thenReturn(cartList);
        List<CartDetailsDTO> savedCarts = cartService.getAllOrders();
        assertEquals(savedCarts.size(),2);
    }

    @Test
    void findCartById() {
        when(cartRepository.findById(c1.getCartID())).thenReturn(Optional.ofNullable(c1));
        CartDetailsDTO foundCart = cartService.findOrderById(c1.getCartID());
        assertEquals(foundCart.getUserID(), c1.getUser().getUserID());
    }

    @Test
    void deleteCart() {
        cartRepository.deleteById(c1.getCartID());
        CartDetailsDTO deletedCart = cartService.deleteCart(c1.getCartID(), true);
        assertNull(deletedCart);
    }

    @Test
    void updateStatus() {
        c1.setStatus(OrderStatus.CANCELED);
        when(cartRepository.save(any(Cart.class))).thenReturn(c1);
        CartDetailsDTO savedCart = cartService.updateStatus(c1.getCartID(), "CANCELED");
        assertEquals(savedCart.getStatus(), c1.getStatus());
    }
}