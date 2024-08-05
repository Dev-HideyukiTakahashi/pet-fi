package com.petfi.track.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petfi.track.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
