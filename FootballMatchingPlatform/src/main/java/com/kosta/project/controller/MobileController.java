package com.kosta.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.dto.TeamDTO;
import com.kosta.project.service.FastMatchingService;
import com.kosta.project.service.MainPageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.kosta.project.service.TeamService;
import com.kosta.project.service.MatchingService;


@Controller
@RequiredArgsConstructor
public class MobileController {
	
	private final FastMatchingService fms;
	private final MainPageService mps;
	private final TeamService ts;
	private final MatchingService ms;
	
	@GetMapping("/fastmatchinglist")
	public String getFastMatchingList(Model model) {
		model.addAttribute("fastmatchinglist", fms.getFastMatchingList());
		
		model.addAttribute("fastmatchinglistS", fms.getFastMatchingListBySmall());
		
		model.addAttribute("fastmathinglistB", fms.getFastMatchingListByBig());
		
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
	
	@GetMapping("/addScore")
	public String getAddScore() {
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
	public String getJoin() {
		return "join";
	}
	
/*	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}*/
	
	@GetMapping("/mainPage")
	public String getMainPage(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		
		String userId = "user001";
		
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
	public String getMatchingList(Model model, String addType, String matchingDate, String matchingTime) {
		MatchingConditionDTO mcDTO = MatchingConditionDTO.builder()
									.addType(addType)
									.matchingDate(matchingDate)
									.matchingTime(matchingTime)
									.build();
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
	
	@GetMapping("/myPageInquiryDetail")
	public String getMyPageInquiryDetail() {
		return "mypage_inquiry-detail";
	}
	
	@GetMapping("/myPageInquiry")
	public String getMyPageInquiry() {
		return "mypage_inquiry";
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
	public String getApplyList(Model model) {
		model.addAttribute("applyList", ts.getApplyList("user00104"));
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
