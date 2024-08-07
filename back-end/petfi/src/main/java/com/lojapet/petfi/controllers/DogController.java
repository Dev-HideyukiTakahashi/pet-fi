package com.lojapet.petfi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lojapet.petfi.entities.Dog;
import com.lojapet.petfi.services.DogService;

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

  @PostMapping
  public ResponseEntity<Dog> insert(@RequestBody Dog entity) {
    entity = dogService.insert(entity);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(entity.getId())
        .toUri();
    return ResponseEntity.created(uri).body(entity);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Dog> update(@PathVariable Long id, @RequestBody Dog entity) {
    entity = dogService.update(id, entity);
    return ResponseEntity.ok(entity);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    dogService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
