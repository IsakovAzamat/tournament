package com.example.mlbb.service;

import com.example.mlbb.entity.Match;
import com.example.mlbb.entity.Team;
import com.example.mlbb.enums.MatchStatus;
import com.example.mlbb.repository.MatchRepository;
import com.example.mlbb.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;


    //создать прак
//    public Match createScrim(CreateScrimRequest request) {
//
//        Team hostTeam = teamRepository.findById(request.getHostTeamId()).orElseThrow(() -> new RuntimeException("Team not found"));
//        Match match = new Match();
//        match.setTeamA(hostTeam);
//        match.setFormat(request.getFormat());
//        match.setNotes(request.getNotes());
//        match.setScheduledTime(request.getStartTime());
//        match.setStatus(MatchStatus.SCHEDULED);
//
//        return matchRepository.save(match);
//    }


    //показ всех праков
    public List<Match> getAllScrim(){
        return matchRepository.findAll();
    }


    //показ открытых праков
    public List<Match> getScrimsByStatusOpen(){
        return matchRepository.findByStatus(MatchStatus.SCHEDULED);
    }


    //принять прак
    public Match acceptScrim(long scrimId, long guestTeamId){
        Match match = matchRepository.findById(scrimId).orElseThrow(() -> new RuntimeException("Scrim not found"));

        if (match.getStatus() != MatchStatus.SCHEDULED){
            throw new RuntimeException("Scrim is not open");
        }

        Team guestTeam = teamRepository.findById(guestTeamId).orElseThrow(() -> new RuntimeException("Team not found"));

        if (match.getTeamA().getId().equals(guestTeamId)){
            throw new RuntimeException("Host team cannot accept its own match");
        }

        match.setTeamB(guestTeam);
        match.setStatus(MatchStatus.WAITING);

        return matchRepository.save(match);
    }


    //отменить прак
    public void cancelScrim(Long scrimId){
        Match match = matchRepository.findById(scrimId).orElseThrow(() -> new RuntimeException("Scrim not found"));

        if (match.getStatus() == MatchStatus.DISPUTED){
            throw new RuntimeException("Cannot cancel completed scrim");
        }

        match.setStatus(MatchStatus.CANCELED);
        matchRepository.save(match);
    }
}
