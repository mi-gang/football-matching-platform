package com.kosta.project.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.MatchingScheduleListDTO;
import com.kosta.project.dto.UserMatchingInfoDTO;
import com.kosta.project.dto.UserPlayInfoDTO;

@Mapper
public interface MatchingMapper2 {
	
	Collection<MatchingScheduleListDTO> selectMatchingListByMonth(String userId, int month);
	Collection<MatchingScheduleListDTO> selectMatchingListByDate(String userId, String date);
	
	int selectMatchingListCount(String userId);
	Collection<MatchingScheduleListDTO> selectMatchingList(String userId);
	boolean updatePayStatus(UserMatchingInfoDTO userMatchingInfoDTO); // String userId, int matchingSeq
	boolean deleteMatching(int matchingAddListSeq);
	
	// 상대팀 평가
	Collection<UserPlayInfoDTO> selectOpposingTeamPlayerList (UserMatchingInfoDTO userMatchingInfoDTO); // String userId, int matchingSeq
	boolean updateReviewScore(UserMatchingInfoDTO userMatchingInfoDTO); // String userId, int matchingSeq, int score
	
	
	Collection<UserPlayInfoDTO> selectPlayerList(int matchingSeq);
	
	// 점수 확인
	int selectReviewScore(UserMatchingInfoDTO userMatchingInfoDTO); // String userId, int matchingSeq
	int selectTeamScore(UserMatchingInfoDTO userMatchingInfoDTO); // String userId, int matchingSeq
	
}
