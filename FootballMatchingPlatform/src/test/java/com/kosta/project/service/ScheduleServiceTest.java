package com.kosta.project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.ReportDTO;
import com.kosta.project.dto.UserMatchingInfoDTO;

@SpringBootTest
public class ScheduleServiceTest {

	@Autowired
	ScheduleService scheduleService;
	
	//@Test
	void getMatchingListByMonthTest() {
		System.out.println(scheduleService.getMatchingListByMonth("user001", 7));
	}
	
	@Test
	void getMatchingListByDateTest() {
		System.out.println(scheduleService.getMatchingListByDate("user001","D", "2024-07-10"));
	}
	
	//@Test
	void getMatchingListCountTest() {
		System.out.println(scheduleService.getMatchingListCount("user001"));
	}
	
	//@Test
	void getMatchingListTest() {
//		System.out.println(scheduleService.getMatchingList("user001"));
	}
	
	//@Test
	void setPayStatusTest() {
		scheduleService.setPayStatus(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user00103").build());
	}
	
	//@Test
	void revmoveMatchingTest() {
		scheduleService.removeMatching(195);
	}
	
	//@Test
	void getOpposingTeamPlayerListTest() {
		scheduleService.setPayStatus(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user001").build());
	}
	
	@Test
	void setReviewScoreTest() {
		List<UserMatchingInfoDTO> userMatchingInfoDTOs = new ArrayList<>();
		
		userMatchingInfoDTOs.add(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user00105").score(2).build());
		userMatchingInfoDTOs.add(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user00106").score(1).build());
		userMatchingInfoDTOs.add(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user00107").score(0).build());
		userMatchingInfoDTOs.add(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user00108").score(-1).build());
		userMatchingInfoDTOs.add(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user00109").score(-2).build());
		
		scheduleService.setReviewScore(userMatchingInfoDTOs, 88);
	}
	
	//@Test
	void getPlayerListTest() {
		System.out.println(scheduleService.getMyTeamPlayerList(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user001").build()));
	}

	//@Test
	void addReportTest() {
		scheduleService.addReport(new ReportDTO(0, "비매너 플레이", "경기 중 자꾸 상대팀 선수들에게 침을 뱉습니다.", 2, "user00100", "user00101"));
	}
	
	//@Test
	void getReviewScoreAndTeamScoreTest() {
		scheduleService.getReviewScoreAndTeamScore(UserMatchingInfoDTO.builder().matchingSeq(6).userId("user001").build());
	}
	
	 
	//@Test
	void cancelMatchingTest() {
		scheduleService.cancelMatching(132);
	}
}
