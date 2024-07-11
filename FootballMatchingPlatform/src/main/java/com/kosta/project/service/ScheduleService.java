package com.kosta.project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.project.dto.MatchingScheduleListDTO;
import com.kosta.project.dto.ReportDTO;
import com.kosta.project.dto.UserMatchingInfoDTO;
import com.kosta.project.dto.UserPlayInfoDTO;
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
	public Collection<MatchingScheduleListDTO> getMatchingListByDate(String userId, String date) {

		Collection<MatchingScheduleListDTO> scheduleListDTOs = new ArrayList<MatchingScheduleListDTO>();

		// 특정 날짜에 맞는 매칭 리스트 불러오기
		scheduleListDTOs = matchingMapper.selectMatchingListByDate(userId, date);

		for (MatchingScheduleListDTO matchingScheduleListDTO : scheduleListDTOs) {
			// 팀장 여부 추가하기
			matchingScheduleListDTO.setIsleader(false);
		}
		return scheduleListDTOs;
	}

	/** 매칭 리스트 개수 확인하기 */
	public int getMatchingListCount(String userId) {
		return matchingMapper.selectMatchingListCount(userId);
	}

	/** 매칭 리스트 전체 확인하기 */
	public Collection<MatchingScheduleListDTO> getMatchingList(String userId) {
		return matchingMapper.selectMatchingList(userId);
	}

	/** 결제하기 */
	public void setPayStatus(UserMatchingInfoDTO userMatchingInfoDTO) {
		if (!matchingMapper.updatePayStatus(userMatchingInfoDTO)) {
			throw new RuntimeException("Failed to update pay status for " + userMatchingInfoDTO);
		}
	}

	/** 매칭 취소하기 */
	public void revmoveMatching(int matchingAddListSeq) {
		if (!matchingMapper.deleteMatching(matchingAddListSeq))
			throw new RuntimeException("Failed to delete matching with sequence " + matchingAddListSeq);
	}

	/** 상대팀 리스트 확인하기 */
	public Collection<UserPlayInfoDTO> getOpposingTeamPlayerList(UserMatchingInfoDTO userMatchingInfoDTO) {
		return matchingMapper.selectOpposingTeamPlayerList(userMatchingInfoDTO);
	}

	/** 상대팀 평가 점수 등록하기 */
	@Transactional
	public void setReviewScore(Collection<UserMatchingInfoDTO> userMatchingInfoDTOs) {
		for (UserMatchingInfoDTO userMatchingInfoDTO : userMatchingInfoDTOs) {
			if (!matchingMapper.updateReviewScore(userMatchingInfoDTO))
				throw new RuntimeException("Failed to update review score for " + userMatchingInfoDTO);
		}
	}

	/** 매칭에 참여한 사용자 리스트 확인하기 */
	public Collection<UserPlayInfoDTO> getPlayerList(int matchingSeq) {
		return matchingMapper.selectPlayerList(matchingSeq);
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

}
