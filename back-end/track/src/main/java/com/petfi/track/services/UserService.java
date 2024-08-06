package com.petfi.track.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petfi.track.entities.User;
import com.petfi.track.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(Long id) {
    return userRepository.findById(id).get();
  }

  public User insert(User entity) {
    return userRepository.save(entity);
  }

  @Transactional
  public User update(Long id, User entity) {
    User user = userRepository.getReferenceById(id);
    updateEntity(user, entity);
    return userRepository.save(user);
  }

  private void updateEntity(User user, User entity) {
    user.setLogin(entity.getLogin());
    user.setPassword(entity.getPassword());
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }
}
