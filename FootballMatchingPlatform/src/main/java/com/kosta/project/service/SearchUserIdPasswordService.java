package com.kosta.project.service;

import org.springframework.stereotype.Service;

import com.kosta.project.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchUserIdPasswordService {
	private final UserMapper userMapper;
	//이름, 이메일이 일치하는 아이디 불러오기
	public String getIdByNameAndEmail(String name, String email) {
		return userMapper.selectIdByNameAndEmail(name, email);
	}
	//아이디, 이메일이 일치하는 비밀번호 불러오기
	public String getPasswordByUserIdAndEmail(String userId, String email) {
		return userMapper.selectPasswordByUserIdAndEmail(userId, email);
	}
	//특정 아이디의 비밀번호 변경하기
	public void setPasswordByUserId(String userId, String password) {
		userMapper.updatePasswordByUserId(password, userId);
	}
}
