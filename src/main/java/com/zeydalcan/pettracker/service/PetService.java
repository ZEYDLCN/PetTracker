package com.zeydalcan.pettracker.service;
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable; 
import com.zeydalcan.pettracker.dto.PetDto; 
import com.zeydalcan.pettracker.dto.PetResponseDTO;
import com.zeydalcan.pettracker.model.Pet; 

import java.util.List;
import java.util.Optional;

public interface PetService {

    
    Pet createPet(PetDto petDto); 

    Page<PetResponseDTO> getAllPets(Pageable pageable);

   
    Optional<PetResponseDTO> getPetById(Long id);

    
    Pet updatePet(Long id, PetDto petDto); 

    void deletePet(Long id);
}