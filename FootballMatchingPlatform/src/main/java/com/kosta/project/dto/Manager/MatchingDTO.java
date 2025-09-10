package com.kosta.project.dto.Manager;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchingDTO {
	private int matchingSeq;
    private LocalDate matchingDate;
    private int matchingTime;
    private boolean fastAddStatus;
    private String matchingStatus;
    private String matchingTier;
    private Integer aScore;
    private Integer bScore;
    private int fieldSeq; // fieldSeq만 포함
}
