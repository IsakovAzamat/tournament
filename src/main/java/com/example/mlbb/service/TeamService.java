package com.example.mlbb.service;

import com.example.mlbb.dto.CreateTeamRequest;
import com.example.mlbb.entity.Team;
import com.example.mlbb.enums.Tier;
import com.example.mlbb.repository.TeamRepository;
import com.example.mlbb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;


    public Team createTeam(CreateTeamRequest request) {
        Team team = new Team();
        team.setName(request.getName());
        team.setLogoUrl(request.getLogoUrl());
        team.setTier(Tier.TIER_3);
        team.setRating(1000);

        return teamRepository.save(team);
    }
}
