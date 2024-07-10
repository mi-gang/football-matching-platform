package com.kosta.project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.TeamDTO;

@SpringBootTest
public class TeamMapperTestJuho {
	
	@Autowired
	TeamMapper tm; 
	
	// 내 팀 정보 불러오기(마이페이지)
	@Test
	void selectTeamInfoByUserIdTest() {
		TeamDTO teamDTO = tm.selectTeamInfoByUserId("user002");
		
		System.out.println(teamDTO.getTeamName() + 
							teamDTO.getTeamRank() + 
							teamDTO.getTeamScore() + 
							teamDTO.getTeamTier() + 
							teamDTO.getWinRate());
	}
	
}
