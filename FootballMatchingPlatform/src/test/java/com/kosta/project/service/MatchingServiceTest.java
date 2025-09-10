package com.kosta.project.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.AddMatchingDataDTO;
import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.repository.FieldMapper;
import com.kosta.project.repository.MatchingMapper;
import com.kosta.project.repository.TeamMapper;
import com.kosta.project.service.MatchingService;


@SpringBootTest
public class MatchingServiceTest {
	
	@Autowired
	MatchingService ms;
	
	//@Test
	void isLeaderTest() {
		System.out.println(ms.isLeader("chul01"));
		System.out.println(ms.isLeader("user002"));
	}
	
	//@Test
	void isTeamTest() {
		System.out.println(ms.isTeam("user002"));
	}
	
	//@Test
	public void getMatchingsList() {
		MatchingConditionDTO dto = MatchingConditionDTO.builder()
				.matchingDate("2024-07-15")
				.matchingTime("8")
				.build();
		List<MatchingsDTO> matchingsDTO = ms.getMatchingsList(dto);
		for(int i=0; i<matchingsDTO.size(); i++) {
			System.out.println("최종 매칭" + matchingsDTO.get(i));
		}
	}
	
	//@Test
	public void getMatchingsListByRegion() {
		MatchingConditionDTO dto = MatchingConditionDTO.builder()
				.matchingDate("2024-07-16")
				.matchingTime("8 10")
				.fieldAddress("미추홀구")
				.build();
		List<MatchingsDTO> matchingsDTO = ms.getMatchingsListByRegion(dto);
		System.out.println(matchingsDTO);
	}
	
	//@Test
	void getFieldInfoTest() {
		System.out.println(ms.getFieldInfo(7));
	}
	
	//@Test
	void addMatcingsTest() {
		MatchingConditionDTO dto = MatchingConditionDTO.builder()
				.matchingDate("2024-07-10")
				.matchingTime("14")
				.build();
		List<MatchingsDTO> matchingsDTO = ms.getMatchingsList(dto);
		
		AddMatchingDataDTO addDTO = AddMatchingDataDTO.builder()
				.type("개인")
				.userId("user002")
				.userTier("D")
				.mdto(matchingsDTO)
				.build();
		ms.addMatcings(addDTO);
//		ms.addMatcings(addDTO);
//		ms.addMatcings(addDTO);
//		ms.addMatcings(addDTO);
//		ms.addMatcings(addDTO);
//		ms.addMatcings(addDTO);
//		ms.addMatcings(addDTO);
//		ms.addMatcings(addDTO);
//		ms.addMatcings(addDTO);
	}
	
	@Test
	void getMatchingAddResultTest() {
		AddMatchingDataDTO addDTO = AddMatchingDataDTO.builder()
				.type("개인")
				.userId("user00101")
				.build();
		System.out.println(ms.getMatchingAddResult(addDTO));
		
	}
}