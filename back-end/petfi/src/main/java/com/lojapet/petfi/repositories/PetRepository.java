package com.lojapet.petfi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojapet.petfi.entities.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
