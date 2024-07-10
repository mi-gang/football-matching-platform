package com.kosta.project.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.UserDTO;

@Mapper
public interface UserMapper {
	
	// 유저 로그인
	UserDTO selectUserLogin(String userId);
	
	// 유저 구글 로그인
	UserDTO selectUserSnsLogin(String userId);
	
	// 유저 마지막 로그인 날짜
	void setUserLastLoginDateByUserId(String userId);
	
	// 아이디 중복 체크용 유저 아이디 불러오기
	String selectUserIdByUserId(String userId);
	
	// 닉네임 중복 체크용 닉네임 불러오기
	String selectUserNicknameByNickname(String nickname);
	
	// 이메일 중복 체크용 이메일 불러오기
	String selectEmailByEmail(String email);
	
	// 회원가입하기
	void insertUserJoin(UserDTO userDTO); // DTO 내에 들어가는 데이터들은 업무 명세서에 자세히 나와있습니다. 개인 순위는 기본값인 0으로 들어갑니다.
	
	// 이름, 이메일 일치하는 아이디 불러오기
	String selectIdByNameAndEmail(String name, String email);
	
	// 아이디, 이메일 일치하는 비밀번호 불러오기
	String selectPasswordByUserIdAndEmail(String userId, String email);
	
	// 비밀번호 업데이트하기
	void updatePasswordByUserId(String password, String userId);
	
	// 개인 순위 top3 불러오기
	List<UserDTO> selectTopThreeUsersList();
	
	// 개인 순위 top100 불러오기
	List<UserDTO> selectTopHundredUsersList(); 
	
	
}
