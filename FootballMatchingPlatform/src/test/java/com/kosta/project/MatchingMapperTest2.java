package com.kosta.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.UserMatchingInfoDTO;
import com.kosta.project.dto.UserPlayInfoDTO;
import com.kosta.project.repository.MatchingMapper2;

@SpringBootTest
public class MatchingMapperTest2 {
	@Autowired
	MatchingMapper2 matchingMapper2;
	
	@Test
	void selectMatchingListByMonthTest() {
		Map<Integer, LocalDate> map = new HashMap();
		map = matchingMapper2.selectMatchingListByMonth("chul01", 7);
		System.out.println(map);
	}
	
	@Test
	void updatePayStatusTest() {
		boolean result = false;
		result = matchingMapper2.updatePayStatus(UserMatchingInfoDTO.builder().matchingSeq(1).userId("chul01").build());
		System.out.println(result);
	}
	
	@Test
	void deleteMatching() {
		boolean result = false;
		result = matchingMapper2.deleteMatching(1);
		System.out.println(result);
	}
	
	@Test
	void selectOpposingTeamPlayerListTest() {
		Collection<UserPlayInfoDTO> infoDTOs = new ArrayList<UserPlayInfoDTO>();
		infoDTOs = matchingMapper2.selectOpposingTeamPlayerList(UserMatchingInfoDTO.builder().matchingSeq(1).userId("chul01").build());
		System.out.println(infoDTOs);
	}
	
	@Test
	void updateReviewScoreTest() {
		boolean result = false;
		result = matchingMapper2.updateReviewScore(UserMatchingInfoDTO.builder().matchingSeq(1).userId("chul01").score(3).build());
		System.out.println(result);
	}
	
	@Test
	void selectPlayerListTest() {
		Collection<UserPlayInfoDTO> infoDTOs = new ArrayList<UserPlayInfoDTO>();
		infoDTOs = matchingMapper2.selectPlayerList(1);
		System.out.println(infoDTOs);
	}
	
	@Test
	void selectReviewScoreTest() {
		int result = matchingMapper2.selectReviewScore(UserMatchingInfoDTO.builder().matchingSeq(1).userId("chul01").build());
		System.out.println(result);
	}
	
	@Test
	void selectTeamScoreTest() {
		int result = matchingMapper2.selectTeamScore(UserMatchingInfoDTO.builder().matchingSeq(1).userId("chul01").build());
		System.out.println(result);
	}
	
}
