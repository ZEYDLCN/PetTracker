package com.zeydalcan.pettracker.repository;

import com.zeydalcan.pettracker.model.Owner; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    
}