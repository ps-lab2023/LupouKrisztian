package com.example.PieseAuto.service.impl;

import com.example.PieseAuto.dto.PartDetailsDTO;
import com.example.PieseAuto.mapper.PartMapper;
import com.example.PieseAuto.model.Part;
import com.example.PieseAuto.repository.PartRepository;
import com.example.PieseAuto.service.PartService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PartServiceImpl implements PartService {
    @Autowired
    private PartRepository partRepository;

    @Override
    public List<Part> getAllParts() { return (List<Part>) partRepository.findAll(); }

    @Override
    public Part findPartById(Long partID) {
        Optional<Part> foundPart = partRepository.findById(partID);
        if(foundPart.isPresent()) {
            return foundPart.get();
        }
        return null;
    }

    @Override
    public Part save(PartDetailsDTO partDetails) {
        Part part = PartMapper.convertToPart(partDetails);
        return partRepository.save(part);
    }

    @Override
    public Part delete(Long partID) {
        Optional<Part> foundPart = partRepository.findById(partID);
        if(foundPart.isPresent()) {
            partRepository.deleteById(partID);
            return foundPart.get();
        }
        return null;
    }

    @Override
    public Part update(PartDetailsDTO partDetails, Long partID) {
        Optional<Part> foundPart = partRepository.findById(partID);
        if(foundPart.isPresent()) {
            Part partNew = PartMapper.convertToPart(partDetails);
            partNew.setPartID(partID);
            return partRepository.save(partNew);
        }
        return null;
    }
}