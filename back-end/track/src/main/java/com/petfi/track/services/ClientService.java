package com.petfi.track.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petfi.track.entities.Client;
import com.petfi.track.repositories.ClientRepository;

@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;

  public List<Client> findAll() {
    return clientRepository.findAll();
  }

  public Client findById(Long id) {
    return clientRepository.findById(id).get();
  }
}
