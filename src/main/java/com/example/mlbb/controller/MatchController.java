package com.example.mlbb.controller;

import com.example.mlbb.dto.AcceptScrimRequest;
import com.example.mlbb.entity.Match;
import com.example.mlbb.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scrims")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

//    @PostMapping
//    public Match createScrim(@RequestBody CreateScrimRequest request) {
//        return matchService.createScrim(request);
//    }
    
    @GetMapping
    public List<Match> getAll(){
        return matchService.getAllScrim();
    }

    @GetMapping("/open")
    public List<Match> getScrimsByStatus(){
        return matchService.getScrimsByStatusOpen();
    }

    @PutMapping("/{id}/accept")
    public Match acceptScrim(
            @PathVariable long id,
            @RequestBody AcceptScrimRequest request
    ){
        return matchService.acceptScrim(id, request.getGuestTeamId());
    }

    @DeleteMapping("/{id}")
    public void cancelScrim(@PathVariable Long id){
        matchService.cancelScrim(id);
    }
}
