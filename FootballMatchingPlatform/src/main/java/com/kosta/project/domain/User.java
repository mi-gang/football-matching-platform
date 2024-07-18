package com.kosta.project.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id", length = 100, nullable = false)
    private String userId; // Unique user identifier

    @Column(name = "password", length = 256, nullable = false)
    private String password; // User password (store securely)

    @Column(name = "nickname", length = 100, nullable = false)
    private String nickname; // User nickname

    @Column(name = "name", length = 30, nullable = false)
    private String name; // User's real name

    @Column(name = "birthday", nullable = false)
    private Date birthday; // User's date of birth

    @Column(name = "gender", length = 1, nullable = false)
    private String gender; // User's gender (e.g., M, F)

    @Column(name = "phone_number", length = 30, nullable = false)
    private String phoneNumber; // User's phone number

    @Column(name = "email", length = 320, nullable = false)
    private String email; // User's email address

    @Column(name = "address", length = 30, nullable = false)
    private String address; // User's address

    @Column(name = "game_count", nullable = false)
    private int gameCount; // Total number of games played

    @Column(name = "win_count", nullable = false)
    private int winCount; // Total number of wins

    @Column(name = "user_rank")
    private Integer userRank; // User ranking (optional)

    @Column(name = "percentile")
    private Float percentile; // User skill percentile (optional)

    @Column(name = "last_login_date")
    private Date lastLoginDate; // Date of last login (optional)

    @Column(name = "join_date", nullable = false)
    private Date joinDate; // User registration date

    @Column(name = "user_score", nullable = false)
    private int userScore; // User's overall score

    @Column(name = "user_tier", length = 1, nullable = false)
    private String userTier; // User tier (e.g., beginner, intermediate, advanced)

    @Column(name = "report_count", nullable = false)
    private int reportCount; // Number of times reported

    @Column(name = "suspended_time")
    private Date suspendedTime; // Suspension date (optional)

    @Column(name = "user_status", length = 1, nullable = false)
    private String userStatus; // User status (e.g., active, suspended)
}