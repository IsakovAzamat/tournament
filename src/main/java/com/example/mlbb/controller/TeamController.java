package com.example.mlbb.controller;


import com.example.mlbb.dto.CreateTeamRequest;
import com.example.mlbb.entity.Team;
import com.example.mlbb.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public Team createTeam(@RequestBody CreateTeamRequest request){
        return teamService.createTeam(request);
    }
}
