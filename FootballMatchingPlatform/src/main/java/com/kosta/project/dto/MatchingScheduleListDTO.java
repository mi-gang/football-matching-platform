package com.kosta.project.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MatchingScheduleListDTO {

	// 이것만 따로 빼는게 나을듯 함
//	private int matchingSeq;
//	private LocalDate matchingDate;
//	private String matchingStatus;	
	
	private int matchingSeq;
	private LocalDate matchingDate;
	private LocalDate matchingTime;
	private boolean FastAddStatus;
	private String matchingStatus;	
	private int aScore;
	private int bScore;
	
	private int matchingAddSeq;
	private int matchingAddListSeq;
	private boolean addSuccessStatus;
	private boolean cancelStatus;
	private boolean payStatus;
	private boolean reviewStatus;
	private boolean teamStatus;
	private String team;
	private int playerNumber;
	
}
