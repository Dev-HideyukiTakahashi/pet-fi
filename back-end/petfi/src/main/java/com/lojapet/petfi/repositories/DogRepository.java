package com.lojapet.petfi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojapet.petfi.entities.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

}
