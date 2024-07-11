package com.kosta.project.repository;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.FastMatchingConditionDTO;
import com.kosta.project.dto.FastMatchingDTO;
import com.kosta.project.dto.MatchingAddListsDTO;
import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingCountDTO;
import com.kosta.project.dto.MatchingDTO;
import com.kosta.project.dto.MatchingScheduleListDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.dto.UserMatchingInfoDTO;
import com.kosta.project.dto.UserPlayInfoDTO;
import com.kosta.project.dto.addMatchingsDTO;

@Mapper
public interface MatchingMapper {
	List<MatchingsDTO> selectMatchingsList(MatchingConditionDTO dto);
	List<MatchingsDTO> selectMatchingsListByRegion(MatchingConditionDTO dto);
	void insertMatchings(addMatchingsDTO dto);
	void insertMatchingAdds(String userId);
	void insertMatchingAddsByTeam(int teamSeq);
	int selectMatchingAddSeq();
	String selectMatchingStatus(int matchingSeq);
	void insertMatchingAddLists(MatchingAddListsDTO dto);
	int selectMatchingMemberCount(MatchingCountDTO dto);
	void updateMatchings(int matchingSeq);
	void updateMatchingAddLists(MatchingCountDTO dto);
	List<MatchingConditionDTO> selectMatchingAddResult(int matchingAddSeq);
	List<FastMatchingDTO> selectFastMatchingList();
	List<FastMatchingDTO> selectFastMatchingListBySmall();
	List<FastMatchingDTO> selectFastMatchingListByBig();
	List<FastMatchingDTO> selectFastMatchingListBySmallAndDateAndRegionAndTier(FastMatchingConditionDTO dto);
	List<FastMatchingDTO> selectFastMatchingListByBigAndDateAndRegionAndTier(FastMatchingConditionDTO dto);
	String selectMatchingTier(int matchingSeq);
	
	// 일정표
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
	// 다가오는 경기 일정을 불러옵니다.
	List<MatchingDTO> selectMatchingAlready(String userId);

}
