package com.kosta.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchingsDTO {
	int matchingSeq;
	String matchingDate;
	int matchingTime;
	String fieldAddress;
	String fieldAddressDetail;
	String fieldName;
}
