package com.zeydalcan.pettracker.service;

import com.zeydalcan.pettracker.dto.PetDto;
import com.zeydalcan.pettracker.dto.PetResponseDTO; 
import com.zeydalcan.pettracker.model.Pet;
import com.zeydalcan.pettracker.model.Owner;
import com.zeydalcan.pettracker.repository.PetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.extern.slf4j.Slf4j;

import com.zeydalcan.pettracker.repository.OwnerRepository;
import com.zeydalcan.pettracker.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors; 

@Service
@Slf4j 
public class PetServiceImp implements PetService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public PetServiceImp(PetRepository petRepository, OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    
    @Override
    @Transactional
    public Pet createPet(PetDto petDto) {
        
        log.debug("Attempting to create a new pet with DTO: {}", petDto);

      
        log.debug("Finding owner with ID: {}", petDto.getOwnerId());
        Owner owner = ownerRepository.findById(petDto.getOwnerId())
                .orElseThrow(() -> {
                   
                    log.error("Owner not found with ID: {}. Could not create pet.", petDto.getOwnerId());
                    return new ResourceNotFoundException("Owner", "id", petDto.getOwnerId());
                });

       
        log.debug("Owner found: {}", owner.getName());
       
        Pet pet = new Pet();
        pet.setAge(petDto.getAge());
        pet.setName(petDto.getName());
        pet.setSpecies(petDto.getSpecies());
        pet.setOwner(owner); 
        log.debug("Saving new pet entity: {}", pet); 
        Pet savedPet = petRepository.save(pet);

       
        log.info("Successfully created Pet with ID: {} for Owner ID: {}", savedPet.getId(), owner.getId());

        
        return savedPet;
    }


    @Override
    @Transactional
    public Pet updatePet(Long id, PetDto petDto) {
         Owner owner = ownerRepository.findById(petDto.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner", "id", petDto.getOwnerId()));

        Pet existingPet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", "id", id));

        existingPet.setName(petDto.getName());
        existingPet.setSpecies(petDto.getSpecies());
        existingPet.setAge(petDto.getAge());
        existingPet.setOwner(owner);
        return petRepository.save(existingPet);
    }
   

    @Override
    @Transactional(readOnly = true)
    public Page<PetResponseDTO> getAllPets(Pageable pageable) { 
        log.debug("Fetching pets with pagination/sorting: {}", pageable);

      
        Page<Pet> petPage = petRepository.findAll(pageable);
        log.debug("Found {} pets on page {}/{}", petPage.getNumberOfElements(), pageable.getPageNumber(), petPage.getTotalPages());

        
        Page<PetResponseDTO> petResponseDTOPage = petPage.map(this::mapPetToPetResponseDTO);

        return petResponseDTOPage;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PetResponseDTO> getPetById(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
       
        return optionalPet.map(this::mapPetToPetResponseDTO); 
    }

   
    private PetResponseDTO mapPetToPetResponseDTO(Pet pet) {
        if (pet == null) {
            return null;
        }
        PetResponseDTO dto = new PetResponseDTO();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setSpecies(pet.getSpecies());
        dto.setAge(pet.getAge());
        if (pet.getOwner() != null) { 
            dto.setOwnerId(pet.getOwner().getId());
            dto.setOwnerName(pet.getOwner().getName());
        } else {
           
            dto.setOwnerId(null);
            dto.setOwnerName(null);
        }
        return dto;
    }

    @Override
    @Transactional
    public void deletePet(Long id) {
         Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", "id", id));
        petRepository.deleteById(id);
    }
}