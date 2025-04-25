package com.zeydalcan.pettracker.model; // Paket adınız

import jakarta.persistence.*; // JPA importları
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity // Veritabanı varlığı
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
   
    @OneToMany(
            mappedBy = "owner", 
            cascade = CascadeType.ALL, 
            orphanRemoval = true, 
            fetch = FetchType.LAZY 
    ) 
    private List<Pet> pets = new ArrayList<>(); 
    
    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setOwner(this);
    }

    public void removePet(Pet pet) {
        pets.remove(pet);
        pet.setOwner(null);
    }
}