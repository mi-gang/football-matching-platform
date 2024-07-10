package com.kosta.project;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.MatchingScheduleListDTO;
import com.kosta.project.dto.UserMatchingInfoDTO;
import com.kosta.project.dto.UserPlayInfoDTO;
import com.kosta.project.repository.MatchingMapper2;

@SpringBootTest
public class MatchingMapperTest2 {
	@Autowired
	MatchingMapper2 matchingMapper2;
	
	//@Test
	void selectMatchingListByMonthTest() {
		Collection<MatchingScheduleListDTO> collection = new ArrayList<>();
		collection = matchingMapper2.selectMatchingListByMonth("user001", 7);
		System.out.println(collection);
	}
	
	//@Test
	void selectMatchingListByDateTest() {
		Collection<MatchingScheduleListDTO> collection = new ArrayList<>();
		collection = matchingMapper2.selectMatchingListByDate("user001", "2024-07-10");
		System.out.println(collection);
	}
	
	//@Test
	void selectMatchingListCountTest() {
		int result = matchingMapper2.selectMatchingListCount("user001");
		System.out.println(result);
	}
	
	//@Test
	void selectMatchingListTest() {
		Collection<MatchingScheduleListDTO> collection = new ArrayList<>();
		collection = matchingMapper2.selectMatchingList("user001");
		System.out.println(collection);
	}
	
	//@Test
	void updatePayStatusTest() {
		boolean result = false;
		result = matchingMapper2.updatePayStatus(UserMatchingInfoDTO.builder().matchingSeq(5).userId("user001").build());
		System.out.println(result);
	}
	
	//@Test
	void deleteMatching() {
		boolean result = false;
		result = matchingMapper2.deleteMatching(1);
		System.out.println(result);
	}
	
	//@Test
	void selectOpposingTeamPlayerListTest() {
		Collection<UserPlayInfoDTO> infoDTOs = new ArrayList<UserPlayInfoDTO>();
		infoDTOs = matchingMapper2.selectOpposingTeamPlayerList(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user001").build());
		System.out.println(infoDTOs);
	}
	
	//@Test
	void updateReviewScoreTest() {
		boolean result = false;
		result = matchingMapper2.updateReviewScore(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user001").score(3).build());
		System.out.println(result);
	}
	
	//@Test
	void selectPlayerListTest() {
		Collection<UserPlayInfoDTO> infoDTOs = new ArrayList<UserPlayInfoDTO>();
		infoDTOs = matchingMapper2.selectPlayerList(6);
		System.out.println(infoDTOs);
	}
	
	//@Test
	void selectReviewScoreTest() {
		int result = matchingMapper2.selectReviewScore(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user001").build());
		System.out.println(result);
	}
	
	@Test
	void selectTeamScoreTest() {
		int result = matchingMapper2.selectTeamScore(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user001").build());
		System.out.println(result);
	}
	
}
