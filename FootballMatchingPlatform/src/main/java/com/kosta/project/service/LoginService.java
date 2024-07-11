package com.kosta.project.service;

import org.springframework.stereotype.Service;

import com.kosta.project.dto.UserDTO;
import com.kosta.project.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final UserMapper userMapper;
	
	//로그인하기 (세션에 본인 등급 넣기-상단 네비에 넣을 용도, 시/도)
	public UserDTO getUserLogin(String userId, String password) {
		userMapper.setUserLastLoginDateByUserId(userId); 
		return userMapper.selectUserLogin(userId, password);
	}
	//소셜 로그인하기
	public UserDTO getUserSnsLogin(String userId, String password) {
		userMapper.setUserLastLoginDateByUserId(userId); 
		return userMapper.selectUserSnsLogin(userId,password);
	}
}
