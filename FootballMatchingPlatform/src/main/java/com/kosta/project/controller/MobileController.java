package com.kosta.project.controller;

import java.util.List;

import com.kosta.project.dto.UserMatchingInfoDTO;
import com.kosta.project.service.*;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kosta.project.dto.InquiryDTO;
import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.dto.TeamDTO;
import com.kosta.project.dto.UserDTO;
import com.kosta.project.service.FastMatchingService;
import com.kosta.project.service.MainPageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Log4j2
@Controller
@RequiredArgsConstructor
public class MobileController {
	
	private final FastMatchingService fms;
	private final MainPageService mps;
	private final TeamService ts;
	private final MatchingService ms;
	private final ScheduleService ss;
	private final UserService us;
	private final AuthenticationManager authenticationManager;
	private final UserDetailService userDetailService;

	@GetMapping("/fastmatchinglist")
	public String getFastMatchingList(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		
		
		String userId = user.getUserId();
		System.out.println("세션이 잘 가져와지는지? : " + userId);
		model.addAttribute("fastmatchinglist", fms.getFastMatchingList(userId));
		
		/*
		 * model.addAttribute("fastmatchinglistS", fms.getFastMatchingListBySmall());
		 * 
		 * model.addAttribute("fastmathinglistB", fms.getFastMatchingListByBig());
		 */		
		return "fastmatchinglist";
	}
	
	
	@GetMapping("/fastmatchinginfo/{matchingSeq}")
	public String getFastMatchingInfo(@PathVariable("matchingSeq") int matchingSeq, Model model) {
		model.addAttribute("fastmatchinginfo", fms.getFastMatchingInfo(matchingSeq));
		return "fastmatchinginfo";
	}
	
	
	@GetMapping("/addedFieldList")
	public String getAddedFieldList(Model model) {
		
		model.addAttribute("addedFieldList", mps.getFieldList());
		
		return "addedFieldList";
	}
	
	@GetMapping("/addedFieldInfo/{fieldSeq}")
	public String getAddedFieldInfo(@PathVariable("fieldSeq") int fieldSeq, Model model) {
		
		model.addAttribute("addedFieldInfo", fms.getField(fieldSeq));
		
		return "addedFieldInfo";
	}
	
	@GetMapping("/addScore/{matchingSeq}/{matchingAddListSeq}")
	public String getAddScore(@PathVariable("matchingSeq") int matchingSeq, @PathVariable("matchingAddListSeq") int matchingAddListSeq, Model model) {

		String userId = "user001";

		model.addAttribute("playerList", ss.getOpposingTeamPlayerList(UserMatchingInfoDTO.builder().userId(userId).matchingSeq(matchingSeq).build()));
		model.addAttribute("matchingSeq", matchingSeq);
		model.addAttribute("matchingAddListSeq", matchingAddListSeq);

		return "addScore";
	}	
	
	
	
	@GetMapping("/consent1")
	public String getConsent1() {
		return "consent_detail1";
	}
	
	@GetMapping("/consent2")
	public String getConsent2() {
		return "consent_detail2";
	}
	
	
	@GetMapping("/consent")
	public String getConsent() {
		return "consent";
	}
	
	@GetMapping("/createTeam")
	public String getCreateTeam() {
		return "createTeam";
	}
	
	@GetMapping("/findIDPW")
	public String getFindIDPW() {
		return "findIDPW";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@GetMapping("/login")
	public String loginForm(@RequestParam(required = false) boolean error, Model model) {
		model.addAttribute("loginFailed", error);
		return "login";
	}

//	@PostMapping("/user/login")
//	public String processLogin(@RequestParam String userId, @RequestParam String password, HttpServletRequest request) {
//		try {
//			// 1. 인증 처리
//			Authentication authentication = authenticate(request, userId, password);
//
//			// 2. 인증 성공 시 처리
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			HttpSession session = request.getSession();
//			session.setAttribute("loginUser", authentication.getPrincipal()); // Principal 객체 저장
//
//			// 메인 페이지로 리다이렉트
//			return "redirect:/mainPage";
//		} catch (AuthenticationException e) {
//			// 3. 인증 실패 시 처리
//			// 예외 메시지를 로그에 기록하거나 사용자에게 오류 메시지 표시
//			log.error("Login failed: " + e.getMessage());
//			return "redirect:/login?error=true";
//		}
//	}

	/** 로그인처리 */
	@PostMapping("/user/login")
	public String processLogin(@RequestParam String userId, @RequestParam String password, HttpServletRequest request) {
		try {
			// 1. 인증 토큰 생성
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, password);
			authToken.setDetails(new WebAuthenticationDetails(request));

			// 2. 인증 수행 // 인증 실패 시 에러
			Authentication authentication = authenticationManager.authenticate(authToken);

			// 3. SecurityContextHolder에 인증 정보 설정
			SecurityContextHolder.getContext().setAuthentication(authentication);

			System.out.println("인증여부"+authentication.isAuthenticated());

			// 4. 세션에 사용자 정보 저장
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			HttpSession session = request.getSession();
			session.setAttribute("userDetails", userDetails);
			UserDTO user = us.getUserLogin(userId, password);
			session.setAttribute("loginUser", user);
			log.info("Saving user details to session: {}", userDetails);
			log.info("Saving login user to session: {}", user);

			UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
			System.out.println("Login User: " + loginUser);
			UserDetails storedUserDetails = (UserDetails) session.getAttribute("userDetails");
			System.out.println("Stored User Details: " + storedUserDetails);

			return "redirect:/mainPage";
		} catch (AuthenticationException e) {
			// 인증 실패 시 처리
			return "redirect:/login?error=true";
		}
	}



	//	@PostMapping("/user/login")
//	public String processLogin(@RequestParam String userId, @RequestParam String password, HttpServletRequest request) {
//		UserDTO user = us.getUserLogin(userId, password);
//		if (user != null) {
//			HttpSession session = request.getSession();
//			session.setAttribute("loginUser", user);
//			return "redirect:/mainPage";
//		} else {
//			return "redirect:/login?error=true";
//		}
//	}
//
	@GetMapping("/mainPage")
	public String getMainPage(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		
		String userId = user.getUserId();
		
		
		
		model.addAttribute("getMatchingAlready", mps.getMatchingAlready(userId));
		

		model.addAttribute("getTopOneUser", mps.getTopOneUser());
		
		
		model.addAttribute("getTopTwoUser", mps.getTopTwoUser());
	
		
		model.addAttribute("getTopThreeUser", mps.getTopThreeUser());
		
		
		
		return "mainPage";
	}
	
	@GetMapping("/matching")
	public String getMatching() {
		return "matching";
	}
	
	@GetMapping("/matchinglist")
	public String getMatchingList(Model model, String userId, String addType, String matchingDate, String matchingTime) {
		MatchingConditionDTO mcDTO = MatchingConditionDTO.builder()
									.userId(userId)
									.addType(addType)
									.matchingDate(matchingDate)
									.matchingTime(matchingTime)
									.build();
		System.out.println(mcDTO);
		List<MatchingsDTO> mDTO = ms.getMatchingsList(mcDTO);
		
		model.addAttribute("matchingList", mDTO);
		return "matchinglist";
	}
	
	@GetMapping("/myCalendar")
	public String getMyCalender() {
		return "myCalendar";
	}
	
	@GetMapping("/myMatchingList")
	public String getMyMatchingList() {
		return "myMatchingList";
	}
	
	@GetMapping("/myPageAddinquiry")
	public String getMyPageAddInquiry() {
		return "mypage_addinquiry";
	}
	
	@GetMapping("/myPageCheckPW")
	public String getMyPageCheckPW() {
		return "mypage_check-pw";
	}
	
	@GetMapping("/myPageCheckUser")
	public String getMyPageCheckUser() {
		return "mypage_check-user";
	}
	
	@GetMapping("/myPageEditPW")
	public String getMyPageEditPW() {
		return "mypage_edit-pw";
	}
	
	@GetMapping("/myPageEditInfo")
	public String getMyPageEditInfo() {
		return "mypage_editInfo";
	}
	
	
	@GetMapping("/myPageInquiry")
	public String getMyPageInquiry(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		
		String userId = user.getUserId();
		
		model.addAttribute("getAllInquiry",us.getAllInquiry(userId));		
		
		return "mypage_inquiry";
	}
	
	@GetMapping("/myPageInquiryDetail")
	public String getMyPageInquiryDetail(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		InquiryDTO inquiry = (InquiryDTO) session.getAttribute("inquiry");
		
		String title = inquiry.getTitle();
		String content = inquiry.getInquiryContent();
		String answer = inquiry.getAnswerContent();
		
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		model.addAttribute("answer", answer);
		
		return "mypage_inquiry-detail";
	}
	
	@GetMapping("/myPage")
	public String getMyPage() {
		return "mypage";
	}
	
	@GetMapping("/ranking")
	public String getRanking(Model model) {
		
		model.addAttribute("getTopOneUser", mps.getTopOneUser());
		
		model.addAttribute("getTopHundredUsersList", mps.getTopHundredUsersList());
		
		return "ranking";
	}
	
	@GetMapping("/teamJoinPossible")
	public String getTeamJoinPossible() {
		return "team_JoinPossible";
	}
	
	@GetMapping("/teamMyTeam")
	public String getTeamMyTeam() {
		return "team_myTeam";
	}
	
	@GetMapping("/teamRank")
	public String getTeamRank() {
		return "team_Rank";
	}
	
	@GetMapping("/teamPage")
	public String getTeamPage() {
		return "team_Page";
	}
	
	@GetMapping("/teamMember")
	public String getTeamMember(@RequestParam int teamSeq, Model model) {
		model.addAttribute("memberList", ts.getTeamMemberList(teamSeq));
		return "team_member";
	}
	
	@GetMapping("/applyList")
	public String getApplyList(@SessionAttribute(name = "loginUser", required = false) UserDTO user, Model model) {
		model.addAttribute("applyList", ts.getApplyList(user.getUserId()));
		return "team_applyList";
	}
	
	@GetMapping("/teamCreate")
	public String getTeamCreate(@RequestParam(required = false) Integer teamSeq, Model model) {
		TeamDTO dto = null;
		if(teamSeq == null) {
			dto = new TeamDTO();
		}else {
			int no = teamSeq.intValue();
			dto = ts.getTeamInfoByModal(no);
		}
		model.addAttribute("team", dto);
		return "team_Create";
	}
	
	
	
}
