package com.example.mlbb.entity;

import com.example.mlbb.enums.Tier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int rating;

    @Enumerated(EnumType.STRING)
    private Tier tier;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", tier=" + tier +
                '}';
    }
}
