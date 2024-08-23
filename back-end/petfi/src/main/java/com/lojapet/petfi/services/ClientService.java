package com.lojapet.petfi.services;

import java.util.List;

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
	public Page<ClientDTO> findAllByName(PageRequest pageRequest, String name) {
		Page<Client> list = clientRepository.findByNameContainingIgnoreCase(pageRequest, name);
		return list.map(client -> new ClientDTO(client));
	}

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllByInstagram(PageRequest pageRequest, String instagram) {
		Page<Client> list = clientRepository.findByInstagram(pageRequest, instagram);
		return list.map(client -> new ClientDTO(client));
	}

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllByPhone(PageRequest pageRequest, String phone) {
		Page<Client> list = clientRepository.findByPhone(pageRequest, phone);
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
			Client entity = clientRepository.getReferenceById(id);
			updateEntity(entity, dto);
			entity.setId(id);
			entity = clientRepository.save(entity);
			return new ClientDTO(entity, "update");
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

	@Transactional
	public List<ClientDTO> findAll() {
		List<Client> list = clientRepository.findAll();
		return list.stream().map(client -> new ClientDTO(client)).toList();
	}

}
