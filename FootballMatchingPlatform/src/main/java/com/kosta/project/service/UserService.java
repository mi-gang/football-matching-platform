package com.kosta.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.project.dto.InquiryDTO;
import com.kosta.project.dto.TeamDTO;
import com.kosta.project.dto.UserDTO;
import com.kosta.project.repository.InquiryMapper;
import com.kosta.project.repository.TeamMapper;
import com.kosta.project.repository.UserMapper;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class UserService {


	private final UserMapper um;

	private final TeamMapper tm;
	
	private final InquiryMapper im;
	
	//로그인하기 (세션에 본인 등급 넣기-상단 네비에 넣을 용도, 시/도)
	public UserDTO getUserLogin(String userId, String password) {
		//1. 회원 정보 및 비밀번호 조회
		UserDTO user = um.selectUserLogin(userId, password);
		//2. 회원정보 체크
		if(user != null) {
		um.setUserLastLoginDateByUserId(userId); 
		return um.selectUserLogin(userId, password);
		}
		else return null;
	}
	
	public String setUserLastLoginDateByUserId(String userId) {
		um.setUserLastLoginDateByUserId(userId);
		return "ok";
	}
	
	//소셜 로그인하기
	public UserDTO getUserSnsLogin(String userId, String password) {
		um.setUserLastLoginDateByUserId(userId); 
		return um.selectUserSnsLogin(userId,password);
	}
	
	// 아이디 중복 여부 확인하기
	public boolean getUserIdByUserId(String userId) {
		if (um.selectUserIdByUserId(userId) == null)
			return true;
		else
			return false;
	}

	// 닉네임 중복 여부 확인하기
	public boolean getUserNicknameByNickname(String nickname) {
		System.out.println(nickname);
		if (um.selectUserNicknameByNickname(nickname) > 0)
			return false;
		else return true;	
	}

	// 이메일 중복 여부 확인하기
	public boolean getEmailByEmail(String email) {
		if (um.selectEmailByEmail(email)==null)
			return true;
		else return false;
	}
	
	//회원가입하기
	public void addUserJoin(UserDTO dto) {
		um.insertUserJoin(dto.getUserId(),dto.getPassword(),dto.getNickname(),dto.getName(),dto.getBirthday(),dto.getGender(),dto.getPhoneNumber(),dto.getEmail(),dto.getAddress());
	}
	
	//이름, 이메일이 일치하는 아이디 불러오기
	public String getIdByNameAndEmail(String name, String email) {
		return um.selectIdByNameAndEmail(name, email);
	}
	
	// 내 팀 정보 불러오기(마이페이지)
	public TeamDTO getTeamInfoByUserId(String userId) {
		return tm.selectTeamInfoByUserId(userId);
	}
	
	// 내 등급 불러오기
	public UserDTO getMyTierAndScoreByUserId(String userId) {
		return um.selectMyTierAndScoreByUserId(userId);
	}
	
	// 내 전적 불러오기
	public UserDTO getMyMatchedInfoByUserId(String userId) {
		return um.selectMyMatchedInfoByUserId(userId);
	}
	
	// 내 이메일, 닉네임 불러오기
	public UserDTO getNicknameAndEmailByUserIdForMyPage(String userId) {
		return um.selectNicknameAndEmailByUserIdForMyPage(userId);	
	}
	
	// 내 문의내역 2개(최신 2개) 불러오기
	public List<InquiryDTO> getTwoInquiry(String userId) {
		return im.selectTwoInquiry(userId);
	}
	
	


	// 내 정보 불러오기
	public UserDTO getMyInfoByUserId(String userId) {
		return um.selectMyInfoByUserId(userId);
	}

	// 아이디, 이메일 일치하는 비밀번호 불러오기
	public String getPasswordByUserIdAndEmail(String userId, String password) {
		return um.selectPasswordByUserIdAndEmail(userId, password);
	}

	// 비밀번호 업데이트하기
		public void setPasswordByUserId(String userId, String pw) {
			um.updatePasswordByUserId(userId, pw);
		}

		//비밀번호 확인하기
		public boolean findMyPw(String userId, String pwInput) {
			String pw = um.selectMyPw(userId);
			if (pwInput.equals(pw)) {
				return true;
			}
			else { 
				return false;
			}
		}		
		
		
		// 내 정보 수정하기
		public void setMyInfoByUserId(UserDTO user, UserDTO dto) {
			um.updateMyInfoByUserId(user.getUserId(),dto.getName(),dto.getNickname(),dto.getPhoneNumber(),dto.getEmail());
		}

	// 회원 탈퇴하기
	public void setUserStatusByUserId(UserDTO userDTO) {
		um.updateUserStatusByUserId(userDTO);
	}

	// 내 문의 내역 불러오기(전체)
	public List<InquiryDTO> getAllInquiry(String userId) {
		return im.selectAllInquiry(userId);
	}
	
	// 선택한 문의 내역 상세 보기
	public InquiryDTO getDetailInfoByInquirySeq (int inquirySeq) {
		return im.selectDetailInfoByInquirySeq(inquirySeq);
	}
	
	// 문의 내역 추가하기
	public void addInquiry(InquiryDTO inquiryDTO) {
		im.insertInquiry(inquiryDTO);
	}




}
