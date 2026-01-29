package com.example.mlbb.dto;

import com.example.mlbb.enums.ScrimFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateScrimRequest {

    private Long hostTeamId;
    private LocalDateTime startTime;
    private ScrimFormat format;
    private String notes;
}
