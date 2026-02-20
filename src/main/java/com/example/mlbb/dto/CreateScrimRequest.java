package com.example.mlbb.dto;

import com.example.mlbb.enums.MatchFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateScrimRequest {

    private Long hostTeamId;
    private LocalDateTime startTime;
    private MatchFormat format;
    private String notes;
}
