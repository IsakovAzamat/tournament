package com.example.mlbb.dto;

import com.example.mlbb.enums.MatchFormat;
import com.example.mlbb.enums.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PracResponse {
    private Long id;
    private String creatorName;
    private MatchStatus status;
    private MatchFormat format;
    private LocalDateTime scheduledTime;
    private LocalDateTime startTime;
    private String description;
}
