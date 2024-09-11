package com.lojapet.petfi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojapet.petfi.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

  Page<Client> findByNameContainingIgnoreCase(PageRequest pageRequest, String name);

  Page<Client> findByInstagram(PageRequest pageRequest, String instagram);

  Page<Client> findByPhoneContaining(PageRequest pageRequest, String phone);

}
