package com.zeydalcan.pettracker.controller;

import com.zeydalcan.pettracker.dto.PetDto;
import com.zeydalcan.pettracker.dto.PetResponseDTO;
import com.zeydalcan.pettracker.model.Pet;
import com.zeydalcan.pettracker.service.PetService;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page; // Page import edildi
import org.springframework.data.domain.Pageable; // Pageable import edildi
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Tüm @...Mapping'leri import eder

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

  
    @PostMapping
    public ResponseEntity<Pet> createPet(@Valid @RequestBody PetDto petDto) {
      
        Pet createdPet = petService.createPet(petDto);
        return new ResponseEntity<>(createdPet, HttpStatus.CREATED); 


        
    }

    
    @GetMapping
    public ResponseEntity<Page<PetResponseDTO>> getAllPets(Pageable pageable) { 
        Page<PetResponseDTO> petsPage = petService.getAllPets(pageable);
        return ResponseEntity.ok(petsPage);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDTO> getPetById(@PathVariable Long id) {
        return petService.getPetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @Valid @RequestBody PetDto petDto) {
        try {
            Pet updatedPet = petService.updatePet(id, petDto);
            return ResponseEntity.ok(updatedPet);
        } catch (RuntimeException e) { 
            return ResponseEntity.notFound().build(); 
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        try {
            petService.deletePet(id);
            return ResponseEntity.noContent().build(); // Başarılı silmede 204 No Content döner
        } catch (RuntimeException e) {
             return ResponseEntity.notFound().build();
        }
    }
}