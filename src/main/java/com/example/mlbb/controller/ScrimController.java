package com.example.mlbb.controller;

import com.example.mlbb.dto.AcceptScrimRequest;
import com.example.mlbb.dto.CreateScrimRequest;
import com.example.mlbb.entity.Scrim;
import com.example.mlbb.service.ScrimService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scrims")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class ScrimController {

    private final ScrimService scrimService;

    @PostMapping
    public Scrim createScrim(@RequestBody CreateScrimRequest request) {
        return scrimService.createScrim(request);
    }
    
    @GetMapping
    public List<Scrim> getAll(){
        return scrimService.getAllScrim();
    }

    @GetMapping("/open")
    public List<Scrim> getScrimsByStatus(){
        return scrimService.getScrimsByStatusOpen();
    }

    @PutMapping("/{id}/accept")
    public Scrim acceptScrim(
            @PathVariable long id,
            @RequestBody AcceptScrimRequest request
    ){
        return scrimService.acceptScrim(id, request.getGuestTeamId());
    }

    @DeleteMapping("/{id}")
    public void cancelScrim(@PathVariable Long id){
        scrimService.cancelScrim(id);
    }
}
