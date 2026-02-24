package com.example.mlbb.repository;

import com.example.mlbb.entity.Team;
import com.example.mlbb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    String findUserByMlbbId(String mlbbId);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
