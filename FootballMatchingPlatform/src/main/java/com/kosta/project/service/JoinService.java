package com.kosta.project.service;

import org.springframework.stereotype.Service;

import com.kosta.project.dto.UserDTO;
import com.kosta.project.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService {
	private final UserMapper userMapper;

	// 아이디 중복 여부 확인하기
	public boolean getUserIdByUserId(String userId) {
		if (userMapper.selectUserIdByUserId(userId) == null)
			return true;
		else
			return false;
	}

	// 닉네임 중복 여부 확인하기
	public boolean getUserNicknameByNickname(String nickname) {
		if (userMapper.selectUserNicknameByNickname(nickname) == null)
			return true;
		else return false;
	}

	// 이메일 중복 여부 확인하기
	public boolean getEmailByEmail(String email) {
		if (userMapper.selectEmailByEmail(email)==null)
			return true;
		else return false;
	}
	
	//회원가입하기
	public void addUserJoin(UserDTO userDTO) {
		userMapper.insertUserJoin(userDTO);
	}
}
