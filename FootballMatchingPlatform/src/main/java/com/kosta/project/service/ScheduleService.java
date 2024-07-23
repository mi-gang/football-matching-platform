package com.kosta.project.service;

import java.util.*;

import com.kosta.project.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.project.repository.MatchingMapper;
import com.kosta.project.repository.ReportMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {
	private final MatchingMapper matchingMapper;
	private final ReportMapper reportMapper;

	/** 현재 달 모든 매칭 정보 리스트 확인하기 */
	public Collection<MatchingScheduleListDTO> getMatchingListByMonth(String userId, int month) {
		return matchingMapper.selectMatchingListByMonth(userId, month);
	}

	/** 특정 날짜에 맞는 매칭 리스트 확인하기 */
	public Collection<MatchingScheduleListDTO> getMatchingListByDate(String userId, String userTier, String date) {

		Collection<MatchingScheduleListDTO> scheduleListDTOs = new ArrayList<MatchingScheduleListDTO>();

		// 특정 날짜에 맞는 매칭 리스트 불러오기
		scheduleListDTOs = matchingMapper.selectMatchingListByDate(userId, date);


		for (MatchingScheduleListDTO matchingScheduleListDTO : scheduleListDTOs) {
			int matchingSeq = matchingScheduleListDTO.getMatchingSeq();

			UserMatchingInfoDTO userMatchingInfoDTO = UserMatchingInfoDTO.builder().userId(userId).matchingSeq(matchingSeq).build();
			// 팀장 여부 추가하기
			if (matchingScheduleListDTO.isTeamStatus())
				matchingScheduleListDTO.setIsleader(matchingMapper.isTeamLeader(userMatchingInfoDTO));

			// 상대팀 전부 평가 완료 여부 추가하기(내가 리뷰 작성했을 때만 - 리뷰 작성 안하면 작성 하라고만 띄우기)
			if (matchingScheduleListDTO.isReviewStatus())
				matchingScheduleListDTO.setOpposingTeamReviewStatus(matchingMapper.selectOpposingTeamReviewStatus(userMatchingInfoDTO));

			// 팀 매칭중일때 팀 명 추가하기
			if(matchingScheduleListDTO.isTeamStatus()) {
				List<String> list = matchingMapper.getTeamNames(userMatchingInfoDTO);
				if (list.size() > 0) {
					matchingScheduleListDTO.setMyTeamName(list.get(0));
					matchingScheduleListDTO.setOpposingTeamName(list.get(1));
				}
			}

			// 빠른 매칭 시 명 수 추가하기
			if (matchingScheduleListDTO.isFastAddStatus()){
				matchingScheduleListDTO.setTotalUserCount(matchingMapper.selectMatchingMemberCount
						(MatchingCountDTO.builder().matchingSeq(matchingSeq).userTier(userTier).build()));
			}
		}
		return scheduleListDTOs;
	}

	/** 매칭 리스트 개수 확인하기 */
	public int getMatchingListCount(String userId) {
		return matchingMapper.selectMatchingListCount(userId);
	}

	/** 매칭 리스트 전체 확인하기 */
	public Collection<MatchingScheduleListDTO> getMatchingList(String userId) {

		Collection<MatchingScheduleListDTO> scheduleListDTOs = new ArrayList<MatchingScheduleListDTO>();

		scheduleListDTOs = matchingMapper.selectMatchingList(userId);

		for (MatchingScheduleListDTO matchingScheduleListDTO : scheduleListDTOs) {
			UserMatchingInfoDTO userMatchingInfoDTO = UserMatchingInfoDTO.builder().userId(userId).matchingSeq(matchingScheduleListDTO.getMatchingSeq()).build();
			// 팀장 여부 추가하기
			if (matchingScheduleListDTO.isTeamStatus()) {
				matchingScheduleListDTO.setIsleader(matchingMapper.isTeamLeader(userMatchingInfoDTO));
			}
			// 상대팀 전부 평가 완료 여부 추가하기(내가 리뷰 작성했을 때만 - 리뷰 작성 안하면 작성 하라고만 띄우기)
			if (matchingScheduleListDTO.isReviewStatus()) {
				matchingScheduleListDTO.setOpposingTeamReviewStatus(matchingMapper.selectOpposingTeamReviewStatus(userMatchingInfoDTO));
			}
		}
		return scheduleListDTOs;
	}

	/** 결제하기 */
	public void setPayStatus(UserMatchingInfoDTO userMatchingInfoDTO) {
		if (!matchingMapper.updatePayStatus(userMatchingInfoDTO)) {
			throw new RuntimeException("Failed to update pay status for " + userMatchingInfoDTO);
		}
	}

	/** 매칭 취소하기 (매칭 전) */
	public void removeMatching(int matchingAddListSeq) {

		// cascade 추가 시 살리기

		// 매칭 신청 시퀀스 구하기
//		int matchingAddSeq = matchingMapper.selectMatchingAddSeqByMatchingAddListSeq(matchingAddListSeq);
//
//		if (matchingMapper.selectMatchingAddListSeqCount(matchingAddSeq) == 1){
//			if (!matchingMapper.deleteMatchingAdd(matchingAddSeq))
//				throw new RuntimeException("Failed to delete matchingAdd with sequence " + matchingAddSeq);
//		}else{
//			if (!matchingMapper.deleteMatching(matchingAddListSeq))
//				throw new RuntimeException("Failed to delete matching with sequence " + matchingAddListSeq);
//		}

		if (!matchingMapper.deleteMatching(matchingAddListSeq))
			throw new RuntimeException("Failed to delete matching with sequence " + matchingAddListSeq);
	}
	
	@Transactional
	/** 매칭 취소하기 (매칭 후) */
	public void cancelMatching(int matchingAddListSeq) {
		
		// 매칭 시퀀스 구하기
		int matchingSeq = matchingMapper.selectMatchingSeqByMachingAddListSeq(matchingAddListSeq);
		
		// 매칭 취소 상태 (개인) 등록하기
		if (matchingMapper.updateCancelStatus(matchingAddListSeq)) {
			// 빠른 매칭 등록하기 
			if (!matchingMapper.updateFastAddStatus(matchingSeq))
				throw new RuntimeException("Failed to update fast add status, matchingSeq : " + matchingSeq);			
		}else {
			throw new RuntimeException("Failed to update cancel status, matchingAddListSeq : " + matchingAddListSeq);			
		}
	}

	/** 상대팀 리스트 확인하기 */
	public Collection<UserPlayInfoDTO> getOpposingTeamPlayerList(UserMatchingInfoDTO userMatchingInfoDTO) {
		return matchingMapper.selectOpposingTeamPlayerList(userMatchingInfoDTO);
	}

	@Transactional
	/** 상대팀 평가 점수 등록하기 */
	public void setReviewScore(List<UserMatchingInfoDTO> userMatchingInfoDTOs, int matchingAddListSeq) {

		int matchingSeq = userMatchingInfoDTOs.get(0).getMatchingSeq();

		try {
		    for (UserMatchingInfoDTO userMatchingInfoDTO : userMatchingInfoDTOs) {
				// 각 사용자 별 등수에 맞는 점수 추가하기
		        if (!matchingMapper.updateReviewScore(userMatchingInfoDTO)) {
		            throw new RuntimeException("Failed to update review score for " + userMatchingInfoDTO);
		        }
				if (!matchingMapper.updateUserScore(userMatchingInfoDTO)) {
					throw new RuntimeException("Failed to update user score for " + userMatchingInfoDTO);
				}
		    }
		    // 매칭(개인) - 리뷰 작성 상태 등록하기
		    if (!matchingMapper.updateReviewStatus(matchingAddListSeq)) {
		        throw new RuntimeException("Failed to update review status for matchingSeq: " + matchingSeq);
		    }
		} catch (RuntimeException e) {
		    // 예외 처리 로직
			// handleFailedUpdate(e);
		}
	}

	/** 매칭에 참여한 사용자 리스트 확인하기 */
	public List<UserPlayInfoDTO> getMyTeamPlayerList(UserMatchingInfoDTO userMatchingInfoDTO) {
		return matchingMapper.myTeamPlayerList(userMatchingInfoDTO);
	}

	/** 사용자 신고하기 */
	public void addReport(ReportDTO reportDTO) {
		try {
			if (reportMapper.selectReport(reportDTO)) {
				throw new RuntimeException("Report already exists" + reportDTO);
			} else {
				reportMapper.insertReport(reportDTO);
			}
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Failed to insert report: " + reportDTO, e);
		}
	}

	/** 내 점수 확인하기 */
	public Map<String, Integer> getReviewScoreAndTeamScore(UserMatchingInfoDTO userMatchingInfoDTO) {
		
		Map<String, Integer> map = new HashMap<>();

		int reviewScore = matchingMapper.selectReviewScore(userMatchingInfoDTO);
		int teamScore = matchingMapper.selectTeamScore(userMatchingInfoDTO);

		map.put("reviewScore", reviewScore);
		map.put("teamScore", teamScore);

		return map;
	}
	
	public List<MatchingScheduleListDTO> getFutureMatchingSeq(String userId) {
		return matchingMapper.selectFutureMatchingList(userId);
	}
}
