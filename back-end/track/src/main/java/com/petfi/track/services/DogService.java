package com.petfi.track.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petfi.track.entities.Dog;
import com.petfi.track.repositories.DogRepository;

@Service
public class DogService {

  @Autowired
  private DogRepository dogRepository;

  public List<Dog> findAll() {
    return dogRepository.findAll();
  }

  public Dog findById(Long id) {
    return dogRepository.findById(id).get();
  }
}
