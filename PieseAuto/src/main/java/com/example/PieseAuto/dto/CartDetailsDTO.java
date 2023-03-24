package com.example.PieseAuto.dto;

import com.example.PieseAuto.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailsDTO {
    private Long cartID;
    private Float total;
    private OrderStatus status;
    private Long userID;
}
