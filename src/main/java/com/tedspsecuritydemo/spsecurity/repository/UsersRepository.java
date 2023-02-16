package com.tedspsecuritydemo.spsecurity.repository;

import com.tedspsecuritydemo.spsecurity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByName(String username);
//    Optional<Users> findByUsernameOrEmail(String username, String email);
    Boolean existsByEmail(String email);
}

