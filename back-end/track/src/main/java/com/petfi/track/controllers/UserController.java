package com.petfi.track.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petfi.track.entities.User;
import com.petfi.track.services.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    List<User> users = userService.findAll();
    return ResponseEntity.ok(users);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    User user = userService.findById(id);
    return ResponseEntity.ok(user);
  }
}
