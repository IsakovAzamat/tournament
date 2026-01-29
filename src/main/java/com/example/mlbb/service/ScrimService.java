package com.example.mlbb.service;

import com.example.mlbb.dto.CreateScrimRequest;
import com.example.mlbb.entity.Scrim;
import com.example.mlbb.entity.Team;
import com.example.mlbb.enums.ScrimStatus;
import com.example.mlbb.repository.ScrimRepository;
import com.example.mlbb.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrimService {

    private final ScrimRepository scrimRepository;
    private final TeamRepository teamRepository;


    //создать прак
    public Scrim createScrim(CreateScrimRequest request) {

        Team hostTeam = teamRepository.findById(request.getHostTeamId()).orElseThrow(() -> new RuntimeException("Team not found"));
        Scrim scrim = new Scrim();
        scrim.setHostTeam(hostTeam);
        scrim.setFormat(request.getFormat());
        scrim.setNotes(request.getNotes());
        scrim.setStartTime(request.getStartTime());
        scrim.setStatus(ScrimStatus.OPEN);
        scrim.setCreatedAt(LocalDateTime.now());

        return scrimRepository.save(scrim);
    }


    //показ всех праков
    public List<Scrim> getAllScrim(){
        return scrimRepository.findAll();
    }


    //показ открытых праков
    public List<Scrim> getScrimsByStatusOpen(){
        return scrimRepository.findByStatus(ScrimStatus.OPEN);
    }


    //принять прак
    public Scrim acceptScrim(long scrimId, long guestTeamId){
        Scrim scrim = scrimRepository.findById(scrimId).orElseThrow(() -> new RuntimeException("Scrim not found"));

        if (scrim.getStatus() != ScrimStatus.OPEN){
            throw new RuntimeException("Scrim is not open");
        }

        Team guestTeam = teamRepository.findById(guestTeamId).orElseThrow(() -> new RuntimeException("Team not found"));

        if (scrim.getHostTeam().getId().equals(guestTeamId)){
            throw new RuntimeException("Host team cannot accept its own scrim");
        }

        scrim.setGuestTeam(guestTeam);
        scrim.setStatus(ScrimStatus.ACCEPTED);

        return scrimRepository.save(scrim);
    }


    //отменить прак
    public void cancelScrim(Long scrimId){
        Scrim scrim = scrimRepository.findById(scrimId).orElseThrow(() -> new RuntimeException("Scrim not found"));

        if (scrim.getStatus() == ScrimStatus.COMPLETED){
            throw new RuntimeException("Cannot cancel completed scrim");
        }

        scrim.setStatus(ScrimStatus.CANCELED);
        scrimRepository.save(scrim);
    }
}
