package com.lojapet.petfi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lojapet.petfi.dto.UserDTO;
import com.lojapet.petfi.entities.User;
import com.lojapet.petfi.repositories.UserRepository;
import com.lojapet.petfi.services.exceptions.DatabaseException;
import com.lojapet.petfi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<UserDTO> findAll() {
    List<User> list = userRepository.findAll();
    return list.stream()
        .map(user -> new UserDTO(user))
        .toList();
  }

  @Transactional(readOnly = true)
  public UserDTO findById(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    return new UserDTO(user);
  }

  @Transactional
  public UserDTO insert(UserDTO dto) {
    User entity = UserDTO.toUser(dto);
    entity = userRepository.save(entity);
    return new UserDTO(entity);
  }

  @Transactional
  public UserDTO update(Long id, UserDTO dto) {
    try {
      User user = userRepository.getReferenceById(id);
      updateEntity(user, dto);
      user = userRepository.save(user);
      return new UserDTO(user);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  private void updateEntity(User user, UserDTO dto) {
    user.setLogin(dto.getLogin());
    user.setPassword(dto.getPassword());
  }

  public void deleteById(Long id) {
    if (!userRepository.existsById(id)) {
      throw new ResourceNotFoundException(id);
    }
    try {
      userRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException(e.getMessage());
    }
  }
}
