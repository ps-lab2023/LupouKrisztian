package com.example.PieseAuto.service;

import com.example.PieseAuto.dto.PartDetailsDTO;
import com.example.PieseAuto.model.Part;
import com.example.PieseAuto.repository.PartRepository;
import com.example.PieseAuto.service.impl.PartServiceImpl;
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
public class PartServiceImplTest {
    @Mock
    private PartRepository partRepository;
    @InjectMocks
    private PartServiceImpl partService;

    private Part p1;
    private Part p2;
    private PartDetailsDTO pd1;
    List<Part> partList;

    @BeforeEach
    void setUp() {
        partService = new PartServiceImpl(partRepository);
        partList = new ArrayList<>();
        p1 = new Part((long)1, 420f, "Aripa dreapta", "Dacia", "Logan",
                20,"e bun", "poza1");
        pd1 = new PartDetailsDTO(420f, "Aripa dreapta", "Dacia", "Logan",
                20,"e bun", "poza1");
        p1 = new Part((long)2, 350.99f, "Capota", "BMW", "E37",
                7,"e pentru beemveu", "poza2");
    }

    @Test
    void savePart() {
        when(partRepository.save(any(Part.class))).thenReturn(p1);
        Part savedPart = partService.save(pd1);
        assertEquals(savedPart, p1);
    }

    @Test
    void getAllparts() {
        partList.add(p1);
        partList.add(p2);

        when(partRepository.findAll()).thenReturn(partList);
        List<Part> savedParts = partService.getAllParts();
        assertEquals(savedParts.size(),2);
    }

    @Test
    void update() {
        p1.setCarModel("Solenza");
        when(partRepository.save(any(Part.class))).thenReturn(p1);
        Part savedPart = partService.save(pd1);
        assertEquals(savedPart.getCarModel(), "Solenza");
    }

    @Test
    void delete() {
        partRepository.deleteById(p1.getPartID());
        Part deletedPart = partService.delete(p1.getPartID());
        assertNull(deletedPart);
    }

    @Test
    void findPartById() {
        when(partRepository.findById(p1.getPartID())).thenReturn(Optional.ofNullable(p1));
        Part foundPart = partService.findPartById(p1.getPartID());
        assertEquals(foundPart, p1);
    }
}
