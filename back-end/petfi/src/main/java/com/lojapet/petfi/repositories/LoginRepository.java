package com.lojapet.petfi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojapet.petfi.entities.Login;
import com.lojapet.petfi.entities.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUsername(String login);
}
