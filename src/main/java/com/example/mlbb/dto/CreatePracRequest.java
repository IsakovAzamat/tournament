package com.example.mlbb.dto;

import com.example.mlbb.enums.MatchFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePracRequest {
    private LocalDateTime startTime;
    private MatchFormat format;
    private String description;
}
