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
public class ReportDTO {
	private int reportSeq;
	private String reportType;
	private String reportContent;
	private int matchingSeq;
	private String userId;
	private String reportedUserId;
}
