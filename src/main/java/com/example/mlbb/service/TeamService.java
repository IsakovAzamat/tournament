package com.example.mlbb.service;

import com.example.mlbb.dto.CreateTeamRequest;
import com.example.mlbb.entity.Team;
import com.example.mlbb.entity.User;
import com.example.mlbb.enums.Tier;
import com.example.mlbb.repository.ScrimRepository;
import com.example.mlbb.repository.TeamRepository;
import com.example.mlbb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final ScrimRepository scrimRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;


    public Team createTeam(CreateTeamRequest request) {

        User currentUser = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Team team = new Team();
        team.setName(request.getName());
        team.setImageUrl(request.getImageUrl());
        team.setTier(Tier.TIER_3);
        team.setRating(1000);
        team.setOwner(currentUser);

        return teamRepository.save(team);
    }

    public List<Team> getMyTeams(User user) {
        return teamRepository.findByOwner(user);
    }
}
