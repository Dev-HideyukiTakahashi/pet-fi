package com.lojapet.petfi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojapet.petfi.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
