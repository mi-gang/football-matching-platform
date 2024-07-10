package com.kosta.project.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDTO {
	private String userId;
	private int inquirySeq;
	private String title;
	private String inquiryContent;
	private int answerContentFlag;
	private String answerContent;
}
