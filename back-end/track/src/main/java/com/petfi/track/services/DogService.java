package com.petfi.track.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petfi.track.entities.Dog;
import com.petfi.track.repositories.DogRepository;

import jakarta.transaction.Transactional;

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

  public Dog insert(Dog entity) {
    return dogRepository.save(entity);
  }

  @Transactional
  public Dog update(Long id, Dog entity) {
    Dog dog = dogRepository.getReferenceById(id);
    updateEntity(dog, entity);
    return dogRepository.save(dog);
  }

  private void updateEntity(Dog dog, Dog entity) {
    dog.setName(entity.getName());
    dog.setAdditionalInformation(entity.getAdditionalInformation());
    dog.setQrcode(dog.getQrcode());
    dog.setClient(entity.getClient());
  }

  public void deleteById(Long id) {
    dogRepository.deleteById(id);
  }
}
