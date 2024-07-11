package com.kosta.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.project.dto.MatchingDTO;
import com.kosta.project.dto.UserDTO;
import com.kosta.project.repository.MatchingMapper;
import com.kosta.project.repository.UserMapper;

import lombok.RequiredArgsConstructor;

//@Transactional
@Service
@RequiredArgsConstructor
public class MainPageService {
	
	private final MatchingMapper mm;
	private final UserMapper um;
	
	// 다가오는 경기 일정 불러오기
	public List<MatchingDTO> getMatchingAlready(String userId) {
		return mm.selectMatchingAlready(userId);
	}
	
	// 점수 높은 사용자 3명 불러오기
	public List<UserDTO> getTopThreeUsersList() {
		return um.selectTopThreeUsersList();
	}
	
	
	
	
	
	
}
