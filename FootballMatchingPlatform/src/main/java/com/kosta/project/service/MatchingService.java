package com.kosta.project.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.repository.FieldMapper;
import com.kosta.project.repository.MatchingMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchingService {
	
	private final MatchingMapper mm;
	private final FieldMapper fm;
	
	public List<MatchingsDTO> getMatchingsList(MatchingConditionDTO dto){
		List<MatchingsDTO> resList = null;		
		
		
		// 입력값에 따른 매칭중인 매칭정보 ex) 24-07-10, 14시 입력
		List<MatchingsDTO> matchingList = mm.selectMatchingsList(dto);
		resList = matchingList;
		
		// 이용 가능한 구장번호 가져오기
		List<Integer> possField = fm.selectFieldSeq();
		
		for(int i=0; i<possField.size(); i++) {
			
		}
		
		return resList;
	}
}
