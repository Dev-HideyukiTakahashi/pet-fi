package com.petfi.track.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petfi.track.entities.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

}
