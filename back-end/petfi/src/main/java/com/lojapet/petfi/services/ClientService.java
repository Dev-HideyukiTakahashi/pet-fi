package com.lojapet.petfi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lojapet.petfi.dto.ClientDTO;
import com.lojapet.petfi.entities.Client;
import com.lojapet.petfi.repositories.ClientRepository;
import com.lojapet.petfi.services.exceptions.DatabaseException;
import com.lojapet.petfi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Transactional(readOnly = true)
  public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
    Page<Client> list = clientRepository.findAll(pageRequest);
    return list.map(client -> new ClientDTO(client));
  }

  @Transactional(readOnly = true)
  public ClientDTO findById(Long id) {
    Client entity = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    return new ClientDTO(entity);
  }

  @Transactional
  public ClientDTO insert(ClientDTO dto) {
    Client entity = ClientDTO.toClient(dto);
    entity = clientRepository.save(entity);
    return new ClientDTO(entity);
  }

  @Transactional
  public ClientDTO update(Long id, ClientDTO dto) {
    try {
      Client client = clientRepository.getReferenceById(id);
      updateEntity(client, dto);
      client = clientRepository.save(client);
      return new ClientDTO(client, "update");
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  private void updateEntity(Client client, ClientDTO dto) {
    client.setName(dto.getName());
    client.setPhone(dto.getPhone());
    client.setFacebook(dto.getFacebook());
    client.setInstagram(dto.getInstagram());
    client.setCity(dto.getCity());
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
