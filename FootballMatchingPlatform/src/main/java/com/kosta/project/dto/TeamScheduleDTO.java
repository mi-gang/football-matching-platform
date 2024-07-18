package com.kosta.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeamScheduleDTO {

	private String matchingStatus;
	private String matchingDate;
	private int matchingTime;
	private int matchingSeq;
	private String fieldAddress;
	private String fieldAddressDetail;
	private String fieldName;
	private String teamSeq;
	
	private String rival;
}
