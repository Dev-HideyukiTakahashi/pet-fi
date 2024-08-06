package com.petfi.track.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.petfi.track.entities.Client;
import com.petfi.track.repositories.ClientRepository;
import com.petfi.track.services.exceptions.DatabaseException;
import com.petfi.track.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;

  public List<Client> findAll() {
    return clientRepository.findAll();
  }

  public Client findById(Long id) {
    return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public Client insert(Client entity) {
    return clientRepository.save(entity);
  }

  @Transactional
  public Client update(Long id, Client entity) {
    try {
      Client client = clientRepository.getReferenceById(id);
      updateEntity(client, entity);
      return clientRepository.save(client);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  private void updateEntity(Client client, Client entity) {
    client.setName(entity.getName());
    client.setPhone(entity.getPhone());
    client.setFacebook(entity.getFacebook());
    client.setInstagram(entity.getInstagram());
    client.setCity(entity.getCity());
  }

  public void deleteById(Long id) {
    if (!clientRepository.existsById(id)) {
      throw new ResourceNotFoundException(id);
    }
    try {
      clientRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }
}
