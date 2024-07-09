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
public class TeamMemberDTO {
	
	private int memberSeq;
	private String joinDate;
	private boolean memberStatus;
	private boolean memberDelete;
	private String userId;
	private int teamSeq;
	
}
