package com.kosta.project;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.repository.TeamMapper;
import com.kosta.project.service.MatchingService;


@SpringBootTest
public class MatchingServiceTest {
	
	@Autowired
	MatchingService ms;
	
	@Test
	public void getMatchingsList() {
		MatchingConditionDTO dto = MatchingConditionDTO.builder()
				.matchingDate("2024-07-10")
				.matchingTime(14)
				.build(); 
		
		System.out.println(ms.getMatchingsList(dto));
	}
	
}
