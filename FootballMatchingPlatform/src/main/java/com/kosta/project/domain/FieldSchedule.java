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
@Table(name = "field_schedules")
public class FieldSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldScheduleSeq; // Field schedule sequence (auto-increment)

    @Column(name = "closed_date", nullable = false)
    private Date closedDate; // Date when the field is closed

    @Column(name = "closed_time", nullable = false)
    private int closedTime; // Specific time of closure (might need adjustment)

    @ManyToOne
    @JoinColumn(name = "field_seq", nullable = false)
    private Field fields; // Field this schedule applies to
}
