package com.zeydalcan.pettracker.model;

import jakarta.persistence.*; // JPA importları eklendi/güncellendi
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;
    private int age;

    
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "owner_id") 
    private Owner owner; 

    

}