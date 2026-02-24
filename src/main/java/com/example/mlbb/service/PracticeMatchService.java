package com.example.mlbb.service;

import com.example.mlbb.dto.CreatePracRequest;
import com.example.mlbb.dto.PracResponse;
import com.example.mlbb.entity.PracticeMatch;
import com.example.mlbb.entity.User;
import com.example.mlbb.enums.MatchFormat;
import com.example.mlbb.enums.MatchStatus;
import com.example.mlbb.repository.PracticeMatchRepository;
import com.example.mlbb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PracticeMatchService {

    private final PracticeMatchRepository pracRepository;
    private final UserRepository userRepository;


    @Transactional
    public PracticeMatch createPrac(CreatePracRequest request, String userEmail) {
        User creator = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        PracticeMatch match = PracticeMatch.builder()
                .creator(creator)
                .status(MatchStatus.WAITING)
                .scheduledTime(LocalDateTime.now())
                .startTime(request.getStartTime())
                .format(request.getFormat())
                .description(request.getDescription())
                .build();

        return pracRepository.save(match);
    }

    public List<PracResponse> getWaitingPracs() {
        return pracRepository.findByStatus(MatchStatus.WAITING).stream()
                .map(match -> new PracResponse(
                        match.getId(),
                        match.getCreator().getUsername(),
                        match.getStatus(),
                        match.getFormat(),
                        match.getScheduledTime(),
                        match.getStartTime(),
                        match.getDescription()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public PracticeMatch acceptPrac(Long pracId, String opponentEmail) {
        User opponent = userRepository.findByEmail(opponentEmail)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        PracticeMatch match = pracRepository.findById(pracId)
                .orElseThrow(() -> new IllegalArgumentException("Прак не найден"));

        if (!match.getStatus().equals(MatchStatus.WAITING)) {
            throw new IllegalStateException("Этот прак уже занят или отменен!");
        }

        if (match.getCreator().getId().equals(opponent.getId())) {
            throw new IllegalStateException("Вы не можете принять свой собственный прак!");
        }

        match.setOpponent(opponent);
        match.setStatus(MatchStatus.COMPLETED);

        return pracRepository.save(match);
    }
}
