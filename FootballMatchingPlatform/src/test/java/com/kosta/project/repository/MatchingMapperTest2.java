package com.kosta.project.repository;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.MatchingScheduleListDTO;
import com.kosta.project.dto.UserMatchingInfoDTO;
import com.kosta.project.dto.UserPlayInfoDTO;
import com.kosta.project.repository.MatchingMapper;

@SpringBootTest
public class MatchingMapperTest2 {
	@Autowired
	MatchingMapper matchingMapper;
	
	@Test
	void selectMatchingListByMonthTest() {
		Collection<MatchingScheduleListDTO> collection = new ArrayList<>();
		collection = matchingMapper.selectMatchingListByMonth("user001", 7);
		System.out.println(collection);
	}
	
	//@Test
	void selectMatchingListByDateTest() {
		Collection<MatchingScheduleListDTO> collection = new ArrayList<>();
		collection = matchingMapper.selectMatchingListByDate("user001", "2024-07-18");
		System.out.println(collection);
	}
	
	//@Test
	void selectMatchingListCountTest() {
		int result = matchingMapper.selectMatchingListCount("user001");
		System.out.println(result);
	}
	
	//@Test
	void selectMatchingListTest() {
		Collection<MatchingScheduleListDTO> collection = new ArrayList<>();
		collection = matchingMapper.selectMatchingList("user001");
		System.out.println(collection);
	}
	
	//@Test
	void updatePayStatusTest() {
		boolean result = false;
		result = matchingMapper.updatePayStatus(UserMatchingInfoDTO.builder().matchingSeq(5).userId("user001").build());
		System.out.println(result);
	}
	
	//@Test
	void deleteMatching() {
		boolean result = false;
		result = matchingMapper.deleteMatching(1);
		System.out.println(result);
	}
	
	//@Test
	void selectOpposingTeamPlayerListTest() {
		Collection<UserPlayInfoDTO> infoDTOs = new ArrayList<UserPlayInfoDTO>();
		infoDTOs = matchingMapper.selectOpposingTeamPlayerList(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user001").build());
		System.out.println(infoDTOs);
	}
	
	//@Test
	void updateReviewScoreTest() {
		boolean result = false;
		result = matchingMapper.updateReviewScore(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user001").score(3).build());
		System.out.println(result);
	}

	//@Test
	void updateUserScoreTest() {
		boolean result = false;
		result = matchingMapper.updateUserScore(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user001").score(3).build());
		System.out.println(result);
	}


	//@Test
	void myTeamPlayerListTest() {
		Collection<UserPlayInfoDTO> infoDTOs = new ArrayList<UserPlayInfoDTO>();
		infoDTOs = matchingMapper.myTeamPlayerList(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user001").build());
		System.out.println(infoDTOs);
	}
	
	//@Test
	void selectReviewScoreTest() {
		int result = matchingMapper.selectReviewScore(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user001").build());
		System.out.println(result);
	}
	
	// 추가 메소드 테스트
	
//	int selectMatchingSeqByMachingAddListSeq(int matchingAddListSeq);
//	boolean updateCancelStatus(int matchingAddListSeq);
//	boolean updateFastAddStatus(int matchingSeq);
//	boolean updateReviewStatus(int matchingAddListSeq);
//	boolean selectOpposingTeamReviewStatus(UserMatchingInfoDTO userMatchingInfoDTO);

	
	//@Test
	void selectMatchingSeqByMachingAddListSeqTest() {
		int result = matchingMapper.selectMatchingSeqByMachingAddListSeq(15);
		System.out.println(result);
	}
	
	//@Test
	void updateCancelStatusTest() {
		System.out.println(matchingMapper.updateCancelStatus(15));
	}
	
	//@Test
	void updateFastAddStatusTest() {
		System.out.println(matchingMapper.updateFastAddStatus(6));
	}
	
	//@Test
	void updateReviewStatusTest() {
		System.out.println(matchingMapper.updateReviewStatus(88));
	}
	
	//@Test
	void selectOpposingTeamReviewStatusTest() {
		System.out.println(matchingMapper.selectOpposingTeamReviewStatus(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user001").build()));
	}

//	int selectMatchingAddSeqByMatchingAddListSeq(int matchingAddListSeq);
//	int selectMatchingAddListSeqCount(int matchingAddSeq);
//	boolean deleteMatchingAdd(int matchingAddListSeq);

	//@Test
	void selectMatchingAddSeqByMatchingAddListSeqTest() {
		System.out.println(matchingMapper.selectMatchingAddSeqByMatchingAddListSeq(6));
	}

	//@Test
	void selectMatchingAddListSeqCountTest() {
		System.out.println(matchingMapper.selectMatchingAddListSeqCount(6));
	}

	//@Test
	void getTeamNamesTest() {
		System.out.println(matchingMapper.getTeamNames(UserMatchingInfoDTO.builder().matchingSeq(145).userId("user00120").build()));
	}



}
