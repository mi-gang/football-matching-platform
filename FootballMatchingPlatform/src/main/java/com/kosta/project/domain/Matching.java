package com.kosta.project.domain;

import java.time.LocalDate;
import java.util.Date;

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
@ToString(exclude = "fields")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "matchings")
public class Matching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchingSeq; // Matching sequence (auto-increment)

    @Column(name = "matching_date")
    private LocalDate matchingDate; // Date of the match

    @Column(name = "matching_time", nullable = false)
    private int matchingTime; // Time of the match (might need adjustment)

    @Column(name = "fast_add_status", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean fastAddStatus; // Whether it was a fast add booking

    @Column(name = "matching_status", nullable = false)
    private String matchingStatus; // Status of the match (e.g., pending, confirmed, cancelled)

    @Column(name = "matching_tier")
    private String matchingTier; // Tier of the match (optional)

    @Column(name = "a_score")
    private Integer aScore; // Score of team A (optional)

    @Column(name = "b_score")
    private Integer bScore; // Score of team B (optional)

    @ManyToOne
    @JoinColumn(name = "field_seq", nullable = false)
    private Field fields; // Field where the match takes place


    public void updateScore(Integer a, Integer b){
        this.aScore = a;
        this.bScore = b;
    }
}
