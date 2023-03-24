package com.example.PieseAuto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartDetailsDTO {
    private Float price;
    private String partName;
    private String carBrand;
    private String carModel;
    private Integer partStock;
    private String description;
    private String image;
}
