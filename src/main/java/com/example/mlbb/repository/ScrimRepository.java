package com.example.mlbb.repository;

import com.example.mlbb.entity.Scrim;
import com.example.mlbb.enums.ScrimStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ScrimRepository extends JpaRepository<Scrim, Long> {
    List<Scrim> findByStatus(ScrimStatus status);
}
