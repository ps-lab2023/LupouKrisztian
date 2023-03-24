package com.example.PieseAuto.model;

import com.example.PieseAuto.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long cartID;
    private Float total;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}