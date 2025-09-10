package com.kosta.project.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.FastMatchingConditionDTO;
import com.kosta.project.dto.FastMatchingDTO;
import com.kosta.project.dto.MatchingAddListsDTO;
import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingCountDTO;
import com.kosta.project.dto.MatchingDTO;
import com.kosta.project.dto.MatchingScheduleListDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.dto.PlayerNumberDTO;
import com.kosta.project.dto.UserMatchingInfoDTO;
import com.kosta.project.dto.UserPlayInfoDTO;
import com.kosta.project.dto.addMatchingsDTO;

@Mapper
public interface MatchingMapper {
	List<MatchingsDTO> selectMatchingsListAll(MatchingConditionDTO dto);
	List<MatchingsDTO> selectMatchingsList(MatchingConditionDTO dto);
	List<MatchingsDTO> selectMatchingsListByRegion(MatchingConditionDTO dto);
	void insertMatchings(addMatchingsDTO dto);
	int selectMaxMatchingSeq();
	void insertMatchingAdds(String userId);
	void insertMatchingAddsByTeam(int teamSeq);
	int selectMatchingAddSeq(String userId);
	int selectMatchingAddSeqByTeam(int teamSeq);
	String selectMatchingStatus(int matchingSeq);
	void insertMatchingAddLists(MatchingAddListsDTO dto);
	void insertMatchingAddListsByTeam(MatchingAddListsDTO dto);
	int selectMatchingMemberCount(MatchingCountDTO dto);
	int selectMatchingMemberCountByTeam(MatchingCountDTO dto);
	void updateMatchings(int matchingSeq, String tier);
	void updateMatchingAddLists(MatchingCountDTO dto);
	void updateMatchingAddListsOfTeam(MatchingCountDTO dto);
	List<Integer> selectMatchingMember(MatchingCountDTO dto);
	void updateTeamToA(int matchingAddListSeq);
	void updateTeamToB(int matchingAddListSeq);
	void updatePlayerNumber(PlayerNumberDTO dto);
	List<MatchingConditionDTO> selectMatchingAddResult(int matchingAddSeq);
	List<FastMatchingDTO> selectFastMatchingList(String userId);
	List<FastMatchingDTO> selectFastMatchingListBySmall(String userId);
	List<FastMatchingDTO> selectFastMatchingListByBig(String userId);
	List<FastMatchingDTO> selectFastMatchingListBySmallAndDateAndRegionAndTier(FastMatchingConditionDTO dto);
	List<FastMatchingDTO> selectFastMatchingListByBigAndDateAndRegionAndTier(FastMatchingConditionDTO dto);
	String selectMatchingTier(int matchingSeq);
	
	// 일정표
	List<MatchingScheduleListDTO> selectMatchingListByMonth(String userId, int month);
	boolean isTeamLeader(UserMatchingInfoDTO userMatchingInfoDTO);
	boolean selectOpposingTeamReviewStatus(UserMatchingInfoDTO userMatchingInfoDTO);
	List<String> getTeamNames(UserMatchingInfoDTO userMatchingInfoDTO);
	List<MatchingScheduleListDTO> selectMatchingListByDate(String userId, String date);


	int selectMatchingListCount(String userId);
	List<MatchingScheduleListDTO> selectMatchingList(String userId);
	boolean updatePayStatus(UserMatchingInfoDTO userMatchingInfoDTO); // String userId, int matchingAddListSeq

	int selectMatchingAddSeqByMatchingAddListSeq(int matchingAddListSeq);
	int selectMatchingAddListSeqCount(int matchingAddSeq);
	boolean deleteMatchingAdd(int matchingAddListSeq);
	boolean deleteMatching(int matchingAddListSeq);
	
	int selectMatchingSeqByMachingAddListSeq(int matchingAddListSeq);
	boolean updateCancelStatus(int matchingAddListSeq);
	boolean updateFastAddStatus(int matchingSeq);
	
	// 상대팀 평가
	List<UserPlayInfoDTO> selectOpposingTeamPlayerList (UserMatchingInfoDTO userMatchingInfoDTO); // String userId, int matchingSeq
	boolean updateReviewScore(UserMatchingInfoDTO userMatchingInfoDTO); // String userId, int matchingSeq, int score
	boolean updateUserScore(UserMatchingInfoDTO userMatchingInfoDTO); // String userId, int matchingSeq, int score
	boolean updateReviewStatus(int matchingAddListSeq);// String userId, int matchingSeq
	
	
	List<UserPlayInfoDTO> myTeamPlayerList(UserMatchingInfoDTO userMatchingInfoDTO); // String userId, int matchingSeq
	
	// 점수 확인
	int selectReviewScore(UserMatchingInfoDTO userMatchingInfoDTO); // String userId, int matchingSeq
	int selectTeamScore(UserMatchingInfoDTO userMatchingInfoDTO); // String userId, int matchingSeq
	
	// 다가오는 경기 일정을 불러옵니다.
	MatchingDTO selectMatchingAlready(String userId);

	// 빠른 매칭 결제하기
	boolean insertMatchingAddForFastMatching(String userId);
	boolean insertFastMatchingAndPay(UserMatchingInfoDTO umiDTO);
	int selectFastMatchingCountForPay(int matchingSeq);
	boolean updateFastMatchingStatusByFastMatchingSeq(int matchingSeq);
	
	// 예정된 매칭 시퀀스 리스트 확인
	List<MatchingScheduleListDTO> selectFutureMatchingList(String userId);

}