package com.kosta.project.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.project.dto.InquiryDTO;
import com.kosta.project.dto.TeamDTO;
import com.kosta.project.dto.UserDTO;
import com.kosta.project.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {
	
	private final UserService us;
	
	//로그인하기 (세션에 본인 등급 넣기-상단 네비에 넣을 용도, 시/도)
	@GetMapping("/login")
	public UserDTO getUserLogin(@RequestParam String userId,String password){
		
		return us.getUserLogin(userId, password);
	}
	
	@PostMapping("/login")
	public UserDTO login(@RequestParam String userId,String password, HttpServletRequest request) {
		
		//1. 회원 정보 조회
		
		UserDTO user = us.getUserLogin(userId,password);
		
		//2. 세션에 회원정보 저장
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
		}
		
		return user;
	}
	
	//소셜 로그인하기
	@GetMapping("/snslogin")
	public UserDTO getUserSnsLogin(String userId, String password) {
		return us.getUserSnsLogin(userId, password);
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/login";
	}
	
	// 아이디 중복 여부 확인하기
	@PostMapping("/getUserId")
	public boolean getUserIdByUserId(String userId) {
		return us.getUserIdByUserId(userId);
	}
	
	// 닉네임 중복 여부 확인하기
	@PostMapping("/getUserNickname")
	public boolean getUserNicknameByNickname(@RequestParam String nickname) {
		return us.getUserNicknameByNickname(nickname);
	}
	
	// 이메일 중복 여부 확인하기
	@GetMapping("/getEmail")
	public boolean getEmailByEmail(String email) {
		return us.getEmailByEmail(email);
	}
	
	//회원가입하기
	@PostMapping("/addUserJoin")
	public Map<String, String> addUserJoin(@RequestBody UserDTO dto) {
		us.addUserJoin(dto);
		return Map.of("result","ok");
	}
	
	//이름, 이메일이 일치하는 아이디 불러오기
	@GetMapping("/getIdByNameEmail")
	public Map<String, String> getIdByNameEmail1(@RequestBody UserDTO dto) {
		return Map.of("result", us.getIdByNameAndEmail(dto.getName(), dto.getEmail()));
	}
	
	// 내 팀 정보 불러오기(마이페이지)
	@GetMapping("/getMyTeamInfo")
	public Map<String, TeamDTO> getTeamInfoByUserId(String userId) {
		return Map.of("result", us.getTeamInfoByUserId(userId));
	}
	
	// 내 등급 불러오기
	@GetMapping("/getMyTierAndScore")
	public Map<String, UserDTO> getMyTierAndScoreByUserId(String userId) {
		return Map.of("result", us.getMyTierAndScoreByUserId(userId));
	}
	
	// 내 전적 불러오기
	@GetMapping("/getMyMatchedInfo")
	public Map<String, UserDTO> getMyMatchedInfoByUserId(String userId) {
		return Map.of("result", us.getMyMatchedInfoByUserId(userId));
	}
	
	// 내 이메일, 닉네임 불러오기
	@GetMapping("/getNicknameAndEmail")
	public Map<String, UserDTO> getNicknameAndEmailByUserIdForMyPage(String userId) {
		return Map.of("result", us.getNicknameAndEmailByUserIdForMyPage(userId));
	}
	
	// 내 문의내역 2개(최신 2개) 불러오기
	@GetMapping("/pathTwoInquiry")
	public Map<String, List<InquiryDTO>> getTwoInquiry(String userId) {
		return Map.of("result", us.getTwoInquiry(userId));
	}
	
	// 내 정보 불러오기
	@GetMapping("/getMyInfo")
	public Map<String, UserDTO> getMyInfoByUserId(@RequestBody UserDTO dto) {
		return Map.of("result", us.getMyInfoByUserId(dto.getUserId(), dto.getPassword()));
	}
	
	// 아이디, 이메일 일치하는 비밀번호 불러오기
	@GetMapping("/getPassword")
	public Map<String, String> getPasswordByUserIdAndEmail(@RequestBody UserDTO dto) {
		return Map.of("result", us.getPasswordByUserIdAndEmail(dto.getUserId(), dto.getPassword()));
	}
	
	// 비밀번호 업데이트하기
	@PostMapping("/setPassword")
	public Map<String,String> setPasswordByUserId(@RequestBody UserDTO dto) {
		us.setPasswordByUserId(dto.getPassword(), dto.getUserId());
		return Map.of("result", "ok");
	}
	
	// 내 정보 수정하기
	@PostMapping("/setMyInfo")
	public Map<String, String> setMyInfoByUserId(@RequestBody UserDTO dto){
		us.setMyInfoByUserId(dto);
		return Map.of("result", "ok");
	}
	// 회원 탈퇴하기
	@PostMapping("/setUserStatusWidhdraw")
	public Map<String, String> setUserStatusByUserId(@RequestBody UserDTO dto){
		us.setUserStatusByUserId(dto);
		return Map.of("result", "ok");
	}
	
	// 내 문의 내역 불러오기(전체)
	@GetMapping("/getAllInquiry")
	public Map<String, List<InquiryDTO>> getAllInquiry(String userId) {
		return Map.of("result", us.getAllInquiry(userId));
	}
	
	// 선택한 문의 내역 상세 보기
	@GetMapping("/pathDetailInfo/{}")
	public Map<String, InquiryDTO> getDetailInfoByInquirySeq(int inquirySeq) {
		return Map.of("result", us.getDetailInfoByInquirySeq(inquirySeq));
	}
	
	// 문의 내역 추가하기
	@PostMapping("/addInquiry")
	public Map<String, String> addInquiry(@RequestBody InquiryDTO dto) {
		us.addInquiry(dto);
		return Map.of("result", "ok");
	}
	
}
