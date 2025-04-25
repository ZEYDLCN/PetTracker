package com.zeydalcan.pettracker.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank; 
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class PetDto {

    @NotBlank(message = "Pet name cannot be blank") 
    @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters") 
    private String name;

    @NotBlank(message = "Species cannot be blank")
    private String species;

    @NotNull(message = "Age cannot be null") 
    @Min(value = 0, message = "Age cannot be negative") 
    private int age;

    
    @NotNull(message = "Owner ID cannot be null") 
    private Long ownerId; 
}