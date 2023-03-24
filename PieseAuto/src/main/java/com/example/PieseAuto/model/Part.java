package com.example.PieseAuto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Part {
    @Id
    @GeneratedValue
    private Long partID;
    private Float price;
    private String partName;
    private String carBrand;
    private String carModel;
    private Integer partStock;
    private String description;
    private String image;
}
