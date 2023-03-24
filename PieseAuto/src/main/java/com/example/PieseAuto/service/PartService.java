package com.example.PieseAuto.service;

import com.example.PieseAuto.dto.PartDetailsDTO;
import com.example.PieseAuto.model.Part;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PartService {
    List<Part> getAllParts();
    Part findPartById(Long partID);
    Part save(PartDetailsDTO partDetails);
    Part delete(Long partID);
    Part update(PartDetailsDTO partDetails, Long partID);
}
