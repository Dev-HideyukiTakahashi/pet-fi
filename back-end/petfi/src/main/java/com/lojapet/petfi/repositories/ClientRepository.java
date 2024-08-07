package com.lojapet.petfi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojapet.petfi.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
