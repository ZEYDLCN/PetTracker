package com.zeydalcan.pettracker.dto;

import lombok.Data;

@Data
public class PetResponseDTO {
    private Long id;
    private String name;
    private String species;
    private int age;
    private Long ownerId;    
    private String ownerName;
  
}