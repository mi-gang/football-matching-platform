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
public class TeamDTO {
	
	private float winRate;
	private int teamSeq;		// 팀 Seq
	private String teamName;	// 팀명
	private String hometown;	// 지역
	private String weekType;	// 시간 유형
	private String weekTime;	// 시간 간격
	private String hopeTime;	// 시간대
	private String content;		// 소개글
	private int teamScore;		// 팀 점수
	private String teamTier;	// 팀 등급
	private int teamRank;		// 팀 순위
	private int gameCount;		// 참가 횟수
	private int winCount;		// 승리 횟수	
	private boolean possA;		
	private boolean possB;
	private boolean possC;
	private boolean possD;
	private boolean recruitment;	// 모집 여부
	private boolean teamDismantleStatus;	//팀 해체 여부
	private String leaderID;				// 팀장 아이디
	private String userId;

	private double odds;			// 승률
	private int memberCount;
	
}
