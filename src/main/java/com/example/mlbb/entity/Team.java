package com.example.mlbb.entity;

import com.example.mlbb.enums.Tier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(nullable = false)
    private Integer rating = 1000;

    @ManyToOne
    @JoinColumn(name = "captain_id", nullable = false)
    private User captain;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "owner_id")
//    @JsonBackReference
//    private User owner;
}
