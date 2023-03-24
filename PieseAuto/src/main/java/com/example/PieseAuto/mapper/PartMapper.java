package com.example.PieseAuto.mapper;

import com.example.PieseAuto.dto.PartDetailsDTO;
import com.example.PieseAuto.model.Part;

public class PartMapper {
    public static PartDetailsDTO toPartDetailsDTO(Part part){
        PartDetailsDTO dto = new PartDetailsDTO();
        dto.setPrice(part.getPrice());
        dto.setDescription(part.getDescription());
        dto.setCarModel(part.getCarModel());
        dto.setCarBrand(part.getCarBrand());
        dto.setPartStock(part.getPartStock());
        dto.setPartName(part.getPartName());
        dto.setImage(part.getImage());
        return dto;
    }

    public static Part convertToPart(PartDetailsDTO partDetails){
        Part part = new Part();
        part.setPrice(partDetails.getPrice());
        part.setDescription(partDetails.getDescription());
        part.setCarModel(partDetails.getCarModel());
        part.setCarBrand(partDetails.getCarBrand());
        part.setPartStock(partDetails.getPartStock());
        part.setPartName(partDetails.getPartName());
        part.setImage(partDetails.getImage());
        return part;
    }
}
