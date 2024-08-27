package com.lojapet.petfi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lojapet.petfi.dto.PetDTO;
import com.lojapet.petfi.entities.Client;
import com.lojapet.petfi.entities.Pet;
import com.lojapet.petfi.repositories.ClientRepository;
import com.lojapet.petfi.repositories.PetRepository;
import com.lojapet.petfi.services.exceptions.DatabaseException;
import com.lojapet.petfi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PetService {

  @Autowired
  private PetRepository petRepository;
  
  @Autowired
  private ClientRepository clientRepository;

  @Transactional(readOnly = true)
  public Page<PetDTO> findAllPaged(PageRequest pageRequest) {
    Page<Pet> list = petRepository.findAll(pageRequest);
    return list.map(pet -> new PetDTO(pet));
  }

  @Transactional(readOnly = true)
  public PetDTO findById(Long id) {
    Pet pet = petRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    return new PetDTO(pet);
  }
  
  @Transactional(readOnly = true)
  public Page<PetDTO> findAllByName(String name, PageRequest pageRequest) {
	    Page<Pet> list = petRepository.findByNameContainingIgnoreCase(name, pageRequest);
	    return list.map(pet -> new PetDTO(pet));
	  }

  @Transactional
  public PetDTO insert(Long clientId, PetDTO dto) {
	Client client = clientRepository.getReferenceById(clientId);
    Pet pet = PetDTO.toPet(dto);
    petRepository.save(pet);
    client.addPet(pet);
    pet.setClient(clientRepository.save(client));
    return new PetDTO(pet);
  }

  @Transactional
  public PetDTO update(Long id, PetDTO dto) {
    try {
      Pet pet = petRepository.getReferenceById(id);
      updateEntity(pet, dto);
      pet = petRepository.save(pet);
      System.out.println(" ID: ------------> " + pet.getClient().getId());
      return new PetDTO(pet);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  private void updateEntity(Pet pet, PetDTO dto) {
    pet.setName(dto.getName());
    pet.setSex(dto.getSex());
    pet.setPetType(dto.getPetType());
    pet.setAdditionalInformation(dto.getAdditionalInformation());
    pet.setPhoto(dto.getPhoto());
    pet.setWanted(dto.isWanted());
  }

  public void deleteById(Long id) {
    if (!petRepository.existsById(id)) {
      throw new ResourceNotFoundException(id);
    }
    try {
      petRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }
}
