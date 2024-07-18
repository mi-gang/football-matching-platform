package com.kosta.project.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "matching_adds")
public class MatchingAdd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingAddSeq; // Matching addition sequence (auto-increment)

    @Column(name = "matching_add_date", nullable = false)
    private Date matchingAddDate; // Date the user applied to join a match

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    private User user; // User who applied to join (optional)

    @ManyToOne(optional = true)
    @JoinColumn(name = "team_seq")
    private Team teams; // Team the user wants to join (optional)
}