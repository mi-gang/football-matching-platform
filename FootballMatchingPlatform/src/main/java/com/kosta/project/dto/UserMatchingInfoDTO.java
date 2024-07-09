package com.kosta.project.dto;

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
public class UserMatchingInfoDTO {
	private int userId;
	private int matchingSeq;
	private int score;
}
