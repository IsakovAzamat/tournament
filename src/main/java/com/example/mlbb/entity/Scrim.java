package com.example.mlbb.entity;

import com.example.mlbb.enums.ScrimFormat;
import com.example.mlbb.enums.ScrimStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "scrims")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Scrim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // кто создал прак
    @ManyToOne
    @JoinColumn(name = "host_team_id", nullable = false)
    private Team hostTeam;

    // кто принял прак (может быть null)
    @ManyToOne
    @JoinColumn(name = "guest_team_id")
    private Team guestTeam;

    // дата и время
    private LocalDateTime startTime;

    // формат: BO1 / BO3 / BO5
    @Enumerated(EnumType.STRING)
    private ScrimFormat format;

    // статус
    @Enumerated(EnumType.STRING)
    private ScrimStatus status;

    // дополнительная инфа
    private String notes;

    // создание
    private LocalDateTime createdAt;


    @Override
    public String toString() {
        return "Scrim{" +
                "id=" + id +
                ", hostTeam=" + hostTeam +
                ", guestTeam=" + guestTeam +
                ", startTime=" + startTime +
                ", format=" + format +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
