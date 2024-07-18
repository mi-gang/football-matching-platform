package com.kosta.project.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamSeq; // Team sequence (auto-increment)

    @Column(name = "team_name", nullable = false)
    private String teamName; // Team name

    @Column(name = "hometown", nullable = false)
    private String hometown; // Team's hometown

    @Column(name = "week_type", nullable = false)
    private String weekType; // Preferred playing week type (e.g., weekdays, weekends)

    @Column(name = "week_time", nullable = false)
    private String weekTime; // Preferred playing time during the week

    @Column(name = "hope_time", nullable = false)
    private String hopeTime; // Preferred playing time (specific time)

    @Column(name = "content", nullable = false)
    private String content; // Team description or introduction

    @Column(name = "team_score", nullable = false)
    private int teamScore; // Team's overall score

    @Column(name = "team_tier", nullable = false)
    private String teamTier; // Team tier (e.g., beginner, intermediate, advanced)

    @Column(name = "game_count", nullable = false)
    private int gameCount; // Total number of games played

    @Column(name = "win_count", nullable = false)
    private int winCount; // Total number of wins

    @Column(name = "team_rank")
    private Integer teamRank; // Team ranking (optional)

    @Column(name = "possible_a", nullable = false)
    private boolean possibleA; // Availability on option A (meaning unclear, needs clarification)

    @Column(name = "possible_b", nullable = false)
    private boolean possibleB; // Availability on option B (meaning unclear, needs clarification)

    @Column(name = "possible_c", nullable = false)
    private boolean possibleC; // Availability on option C (meaning unclear, needs clarification)

    @Column(name = "possible_d", nullable = false)
    private boolean possibleD; // Availability on option D (meaning unclear, needs clarification)

    @Column(name = "recruitment", nullable = false)
    private boolean recruitment; // Whether the team is recruiting new players

    @Column(name = "team_dismantle_status", nullable = false)
    private boolean teamDisbandStatus; // Whether the team is disbanded

    @ManyToOne
    @JoinColumn(name = "leader_id", nullable = false)
    private User users; // Team leader (foreign key to users table)
}
