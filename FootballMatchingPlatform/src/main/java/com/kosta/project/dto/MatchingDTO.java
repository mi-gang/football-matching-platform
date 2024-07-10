package com.kosta.project.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


// 쓰이는 테이블
/* 
 * matchings
 * fields
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchingDTO {
	private String matchingStatus;
	private Date matchingDate;
	private int matchingTime;
	private String feildAddress;
	private String feildAddressDetail;
	private String feildName;
}
