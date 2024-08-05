package com.petfi.track.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petfi.track.entities.Dog;
import com.petfi.track.services.DogService;

@RestController
@RequestMapping(path = "/dogs")
public class DogController {

  @Autowired
  private DogService dogService;

  @GetMapping
  public ResponseEntity<List<Dog>> findAll() {
    List<Dog> dogs = dogService.findAll();
    return ResponseEntity.ok(dogs);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Dog> findById(@PathVariable Long id) {
    Dog dog = dogService.findById(id);
    return ResponseEntity.ok(dog);
  }
}
