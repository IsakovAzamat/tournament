package com.example.mlbb.repository;

import com.example.mlbb.entity.Team;
import com.example.mlbb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
