package com.petfi.track.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
