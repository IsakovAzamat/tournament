package com.example.mlbb.repository;

import com.example.mlbb.entity.PracticeMatch;
import com.example.mlbb.enums.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PracticeMatchRepository extends JpaRepository<PracticeMatch, Long> {
    List<PracticeMatch> findByStatus(MatchStatus status);
}
