package com.kosta.project.repository;

import org.apache.ibatis.annotations.Mapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.kosta.project.dto.MatchingDTO;
import com.kosta.project.dto.UserDTO;


@Mapper
public interface UserMapper {
	String selectUserStatus(String userId);
	String selectSuspendedTime(String userId);

	/* 메인 */
	// 유저 로그인
	UserDTO selectUserLogin(String userId);
	
	// 유저 구글 로그인
	UserDTO selectUserSnsLogin(String userId, String password);
	
	// 시큐리티용 로그인
	UserDTO findByUserId(String UserId);
	
	// 유저 마지막 로그인 날짜
	void setUserLastLoginDateByUserId(String userId);
	
	// 아이디 중복 체크용 유저 아이디 불러오기
	String selectUserIdByUserId(String userId);
	
	// 닉네임 중복 체크용 닉네임 불러오기
	Integer selectUserNicknameByNickname(String nickname);
	
	// 이메일 중복 체크용 이메일 불러오기
	String selectEmailByEmail(String email);
	
	// 회원가입하기
	// DTO 내에 들어가는 데이터들은 업무 명세서에 자세히 나와있습니다. 개인 순위는 기본값인 0으로 들어갑니다.
	void insertUserJoin(String userId, String password, String nickname, String name, Date birthday, String gender,
			String phoneNumber, String email, String address);
	// 이름, 이메일 일치하는 아이디 불러오기
	String selectIdByNameAndEmail(String name, String email);
	
	// 아이디, 이메일 일치하는 비밀번호 불러오기
	String selectPasswordByUserIdAndEmail(String userId, String email);
	
	// 비밀번호 업데이트하기
	void updatePasswordByUserId(String userId, String pw);

	//비밀번호 확인하기
	String selectMyPw(String userId);	
	
	// 개인 순위 top1 불러오기
	UserDTO selectTopOneUser();
	
	// 개인 순위 top2 불러오기
	UserDTO selectTopTwoUser();
	
	// 개인 순위 top3 불러오기
	UserDTO selectTopThreeUser();
	
	// 개인 순위 top100 불러오기
	List<UserDTO> selectTopHundredUsersList(); 
	
	
	
	
	

	
	/* 마이 페이지 */	
	// 내 정보 수정하기
	void updateMyInfoByUserId(String userId, String name, String nickname, String phoneNumber, String email);
	
	// 회원 상태 수정하기(회원 탈퇴 시)
	void updateUserStatusByUserId(UserDTO userDTO);
	
	// 내 정보 불러오기
	UserDTO selectMyInfoByUserId(String userId);
	
	// 내 등급 불러오기
	UserDTO selectMyTierAndScoreByUserId(String userId);
	
	// 내 전적 불러오기
	UserDTO selectMyMatchedInfoByUserId(String userId);
	
	// 닉네임, 이메일 불러오기
	UserDTO selectNicknameAndEmailByUserIdForMyPage(String userId);
	
	
	
}
