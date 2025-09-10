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
import jakarta.persistence.OneToOne;
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
@Table(name = "matching_add_lists")
public class MatchingAddList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchingAddListSeq; // Matching addition list sequence (auto-increment)

    @ManyToOne
    @JoinColumn(name = "matching_seq", nullable = false)
    private Matching matching; // Matching this entry belongs to

    @Column(name = "matching_success_status", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean matchingSuccessStatus; // Whether the user was successfully added to the match

    @Column(name = "cancel_status", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean cancelStatus; // Whether the addition was cancelled

    @Column(name = "pay_status", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean payStatus; // Whether the user paid for the participation

    @Column(name = "review_status", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean reviewStatus; // Whether a review has been written

    @Column(name = "team_status", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean teamStatus;

    @Column(name = "team", nullable = false)
    private String team; // Team name (might need adjustment)

    @Column(name = "player_number")
    private Integer playerNumber; // Player number assigned (optional)

    @Column(name = "review_score")
    private Integer reviewScore; // Review score provided (optional)

    @OneToOne
    @JoinColumn(name = "matching_add_seq", nullable = false)
    private MatchingAdd matchingAdds; // Reference to the matching addition information
}