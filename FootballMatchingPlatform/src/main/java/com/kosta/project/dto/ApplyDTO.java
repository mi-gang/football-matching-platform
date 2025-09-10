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
public class ApplyDTO {
	
	private int applySeq;
	private String applyDate;
	private boolean applyStatus;
	private boolean closeStatus;
	private String userId;
	private int teamSeq;
	
	// user 정보
	private String nickname;
	private String birthday;
	private String gender;
	private int age;
	private String userTier;
	private String email;
}
