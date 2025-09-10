package com.kosta.project.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.project.dto.FastMatchingConditionDTO;
import com.kosta.project.dto.FastMatchingDTO;
import com.kosta.project.dto.FieldDTO;
import com.kosta.project.dto.FieldsDTO;
import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.dto.UserMatchingInfoDTO;
import com.kosta.project.repository.FieldMapper;
import com.kosta.project.repository.MatchingMapper;
import com.kosta.project.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FastMatchingService {

	private final UserMapper um;
	private final FieldMapper fm;
	private final MatchingMapper mm;
	
	// 회원 상태 확인
	public String getUserStatus(String userId) {
		return um.selectUserStatus(userId);
	}
	
	
	// 정지 여부 확인 (결제 시)
	public boolean isSuspendedUser(String userId) {
		boolean result = false;
		 LocalDate today = LocalDate.now();
		 LocalDate suspended = LocalDate.parse(um.selectSuspendedTime(userId));
		if(today.isBefore(suspended)) {
			result = true;
		}
		
		
		return result;
	}
	
	


	
	
	
	// 구장 상세 정보 조회
	public FieldDTO getField(int fieldSeq) {
		return fm.selectField(fieldSeq);
	}
	
	
	// 빠른 신청용 구장 상세 정보 조회
	public FieldsDTO getFastMatchingInfo(int matchingSeq) {
		return fm.selectFastMatchingInfo(matchingSeq);
	}
	
	
	
	// 등급확인
	public String getMatchingTier(int matchingSeq) {
		return mm.selectMatchingTier(matchingSeq);
	}
	
	
	// 빠른 신청 리스트 
	public List<FastMatchingDTO> getFastMatchingList(String userId) {
		return mm.selectFastMatchingList(userId);
	}
	
	
	// 빠른 신청 리스트 Small
	public List<FastMatchingDTO> getFastMatchingListBySmall(String userId) {
		return mm.selectFastMatchingListBySmall(userId);
	}
	
	
	// 빠른 신청 리스트 Big
	public List<FastMatchingDTO> getFastMatchingListByBig(String userId) {
		return mm.selectFastMatchingListByBig(userId);
	}
	
	
	// 빠른 신청 리스트 조회(인원 많은 순, 가까운 일정 순, 날짜 지역 등급 필터) Big
	public List<FastMatchingDTO> getFastMatchingListByBigAndDateAndRegionAndTier(FastMatchingConditionDTO fastMatchingConditionDTO ) {
		return mm.selectFastMatchingListByBigAndDateAndRegionAndTier(fastMatchingConditionDTO);
	}
	
	
	// 빠른 신청 리스트 조회(인원 적은 순, 가까운 일정 순, 날짜 지역 등급 필터) Small
	public 	List<FastMatchingDTO> getFastMatchingListBySmallAndDateAndRegionAndTier(FastMatchingConditionDTO dto) {
		return mm.selectFastMatchingListBySmallAndDateAndRegionAndTier(dto);
	}
	
	// 결제하기
	public boolean setTryPay(UserMatchingInfoDTO umiDTO) {
		boolean result = false;
		int playerCount = mm.selectFastMatchingCountForPay(umiDTO.getMatchingSeq());
		
		if(playerCount < 10) {
		
			if(mm.insertMatchingAddForFastMatching(umiDTO.getUserId()) && mm.insertFastMatchingAndPay(umiDTO)) {
				result = true;
			}
		}
		
		if(mm.selectFastMatchingCountForPay(umiDTO.getMatchingSeq()) == 10) {
			mm.updateFastMatchingStatusByFastMatchingSeq(umiDTO.getMatchingSeq());
		}
		
		
		return result;
	}
	
	
}
