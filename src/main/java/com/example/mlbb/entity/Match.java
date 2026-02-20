package com.example.mlbb.entity;

import com.example.mlbb.enums.MatchFormat;
import com.example.mlbb.enums.MatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_a_id", nullable = false)
    private Team TeamA;

    @ManyToOne
    @JoinColumn(name = "team_b_id", nullable = false)
    private Team TeamB;

    @Column(nullable = false)
    private LocalDateTime scheduledTime;

    // формат: BO1 / BO3 / BO5
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchFormat format;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchStatus status = MatchStatus.SCHEDULED;

    private Integer finalScoreTeamA;
    private Integer finalScoreTeamB;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Team winner;
}
