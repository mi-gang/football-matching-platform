package com.kosta.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MatchingsDTO {
	int matchingSeq;
	String matchingDate;
	int matchingTime;
	String fieldAddress;
	String fieldAddressDetail;
	String fieldName;
	int fieldSeq;
}