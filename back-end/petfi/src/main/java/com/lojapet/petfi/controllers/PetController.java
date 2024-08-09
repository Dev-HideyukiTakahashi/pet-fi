package com.lojapet.petfi.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lojapet.petfi.dto.PetDTO;
import com.lojapet.petfi.services.PetService;

@RestController
@RequestMapping(path = "/pets")
public class PetController {

  @Autowired
  private PetService petService;

  @GetMapping
  public ResponseEntity<Page<PetDTO>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "15") Integer linesPerPage,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    Page<PetDTO> list = petService.findAllPaged(pageRequest);
    return ResponseEntity.ok(list);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<PetDTO> findById(@PathVariable Long id) {
    PetDTO dto = petService.findById(id);
    System.out.println("---------------CONTROLLER-----------------");
    System.out.println(dto.getQrcode());
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  public ResponseEntity<PetDTO> insert(@RequestBody PetDTO dto) {
    dto = petService.insert(dto);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(dto.getId())
        .toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<PetDTO> update(@PathVariable Long id, @RequestBody PetDTO dto) {
    dto = petService.update(id, dto);
    return ResponseEntity.ok(dto);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    petService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
