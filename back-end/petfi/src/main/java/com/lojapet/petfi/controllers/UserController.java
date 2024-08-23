package com.lojapet.petfi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lojapet.petfi.dto.UserDTO;
import com.lojapet.petfi.services.UserService;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin("*")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll() {
    List<UserDTO> users = userService.findAll();
    return ResponseEntity.ok(users);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
    UserDTO dto = userService.findById(id);
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto) {
    dto = userService.insert(dto);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(dto.getId())
        .toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto) {
    dto = userService.update(id, dto);
    return ResponseEntity.ok(dto);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    userService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
