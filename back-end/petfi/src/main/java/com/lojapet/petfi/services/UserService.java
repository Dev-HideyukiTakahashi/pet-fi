package com.lojapet.petfi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lojapet.petfi.entities.User;
import com.lojapet.petfi.repositories.UserRepository;
import com.lojapet.petfi.services.exceptions.DatabaseException;
import com.lojapet.petfi.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
  }

  public User insert(User entity) {
    return userRepository.save(entity);
  }

  @Transactional
  public User update(Long id, User entity) {
    try {
      User user = userRepository.getReferenceById(id);
      updateEntity(user, entity);
      return userRepository.save(user);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException(id);
    }
  }

  private void updateEntity(User user, User entity) {
    user.setLogin(entity.getLogin());
    user.setPassword(entity.getPassword());
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
