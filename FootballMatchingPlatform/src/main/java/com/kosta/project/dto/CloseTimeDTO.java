package com.kosta.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CloseTimeDTO {
	String closedDate;
	String closedTime;
	int fieldSeq;
}
