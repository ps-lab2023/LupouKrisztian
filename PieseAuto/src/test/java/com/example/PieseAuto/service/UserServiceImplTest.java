package com.example.PieseAuto.service;

import com.example.PieseAuto.dto.AuthDTO;
import com.example.PieseAuto.dto.UserDetailsDTO;
import com.example.PieseAuto.enums.Role;
import com.example.PieseAuto.model.Cart;
import com.example.PieseAuto.model.User;
import com.example.PieseAuto.repository.UserRepository;
import com.example.PieseAuto.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    private User u1;
    private User u2;
    private UserDetailsDTO ud1;
    private List<Cart> c1;
    private List<Cart> c2;
    private AuthDTO auth;
    List<User> userList;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
        userList = new ArrayList<>();
        c1 = new ArrayList<>();
        c2 = new ArrayList<>();
        u1 = new User((long)1,"Lupul Cristian", "1234", 22, "clupi@yahoo.com",
                "Str. Mehedinti, Cluj-Napoca", "0756678543", Role.ADMIN, c1);
        ud1 = new UserDetailsDTO("Lupul Cristian", "1234", 22, "clupi@yahoo.com",
                "Str. Mehedinti, Cluj-Napoca", "0756678543");
        u2 = new User((long)2,"Popescu Dorel", "1234", 23, "pdorel@gmail.com",
                "Str. Observatorului, Cluj-Napoca", "0756379523", Role.CLIENT, c2);
        auth = new AuthDTO("Lupul Cristian", "1234");
    }

    @Test
    void saveUser() {
        when(userRepository.save(any(User.class))).thenReturn(u1);
        User savedUser = userService.save(ud1);
        assertEquals(savedUser, u1);
    }

    @Test
    void getAllUsers() {
        userList.add(u1);
        userList.add(u2);

        when(userRepository.findAll()).thenReturn(userList);
        List<User> savedUsers = userService.getAllUsers();
        assertEquals(savedUsers.size(),2);
    }

    @Test
    void update() {
        u1.setEmail("klupi@gmail.com");
        when(userRepository.save(any(User.class))).thenReturn(u1);
        User savedUser = userService.save(ud1);
        assertEquals(savedUser.getEmail(), "klupi@gmail.com");
    }

    @Test
    void delete() {
        userRepository.deleteById(u1.getUserID());
        User deletedUser = userService.delete(u1.getUserID());
        assertNull(deletedUser);
    }


    @Test
    void findUserById() {
        when(userRepository.findById(u1.getUserID())).thenReturn(Optional.ofNullable(u1));
        User foundUser = userService.findUserById(u1.getUserID());
        assertEquals(foundUser, u1);
    }

    @Test
    void goodLogin() {
        when(userRepository.findFirstByUserNameAndPassword(u1.getUserName(), u1.getPassword())).thenReturn(u1);
        User loggedUser = userService.logIn(auth);
        assertEquals(loggedUser, u1);
    }
}
