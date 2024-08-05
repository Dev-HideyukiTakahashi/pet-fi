package com.petfi.track.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petfi.track.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
