package com.kosta.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class addMatchingsDTO {
	String matchingDate;
	int matchingTime;
	int fieldSeq;
}
