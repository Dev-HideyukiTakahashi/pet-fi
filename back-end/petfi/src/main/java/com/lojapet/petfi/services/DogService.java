package com.lojapet.petfi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lojapet.petfi.entities.Dog;
import com.lojapet.petfi.repositories.DogRepository;
import com.lojapet.petfi.services.exceptions.DatabaseException;
import com.lojapet.petfi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class DogService {

  @Autowired
  private DogRepository dogRepository;

  public List<Dog> findAll() {
    return dogRepository.findAll();
  }

  public Dog findById(Long id) {
    return dogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public Dog insert(Dog entity) {
    return dogRepository.save(entity);
  }

  @Transactional
  public Dog update(Long id, Dog entity) {
    try {
      Dog dog = dogRepository.getReferenceById(id);
      updateEntity(dog, entity);
      return dogRepository.save(dog);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(id);
    }

  }

  private void updateEntity(Dog dog, Dog entity) {
    dog.setName(entity.getName());
    dog.setAdditionalInformation(entity.getAdditionalInformation());
    dog.setQrcode(dog.getQrcode());
    dog.setClient(entity.getClient());
  }

  public void deleteById(Long id) {
    if (!dogRepository.existsById(id)) {
      throw new ResourceNotFoundException(id);
    }
    try {
      dogRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }
}
