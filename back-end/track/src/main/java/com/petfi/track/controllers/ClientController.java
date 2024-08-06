package com.petfi.track.controllers;

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

import com.petfi.track.entities.Client;
import com.petfi.track.services.ClientService;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @GetMapping
  public ResponseEntity<List<Client>> findAll() {
    List<Client> clients = clientService.findAll();
    return ResponseEntity.ok(clients);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Client> findById(@PathVariable Long id) {
    Client client = clientService.findById(id);
    return ResponseEntity.ok(client);
  }

  @PostMapping
  public ResponseEntity<Client> insert(@RequestBody Client client) {
    client = clientService.insert(client);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(client.getId())
        .toUri();
    return ResponseEntity.created(uri).body(client);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
    client = clientService.update(id, client);
    return ResponseEntity.ok(client);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    clientService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
