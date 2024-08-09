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

import com.lojapet.petfi.dto.ClientDTO;
import com.lojapet.petfi.services.ClientService;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @GetMapping
  public ResponseEntity<Page<ClientDTO>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    Page<ClientDTO> list = clientService.findAllPaged(pageRequest);
    return ResponseEntity.ok(list);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
    ClientDTO client = clientService.findById(id);
    return ResponseEntity.ok(client);
  }

  @PostMapping
  public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto) {
    dto = clientService.insert(dto);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(dto.getId())
        .toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dto) {
    dto = clientService.update(id, dto);
    return ResponseEntity.ok(dto);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    clientService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
