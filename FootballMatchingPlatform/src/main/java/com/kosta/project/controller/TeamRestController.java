package com.kosta.project.controller;

import java.util.List;
import java.util.Map;

import org.apache.coyote.Request;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.thymeleaf.TemplateEngine;

import com.kosta.project.dto.TeamDTO;
import com.kosta.project.dto.TeamMemberDTO;
import com.kosta.project.dto.TeamScheduleDTO;
import com.kosta.project.dto.UserDTO;
import com.kosta.project.service.TeamService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamRestController {

	private final TeamService ts;
	
	// 팀 유무 확인
	@GetMapping("/isTeam")
	public Map<String, String> isTeam(@SessionAttribute(name = "loginUser", required = false) UserDTO user){
		String id = ts.isTeam(user.getUserId());
		if(id == null)
			id = " ";
		return Map.of("result", id);
	}
	
	//팀 생성하기
	@PostMapping("/createTeam")
	public Map<String, Boolean> addTeam(@SessionAttribute(name = "loginUser", required = false) UserDTO user ,@RequestBody TeamDTO dto){
		String userId = user.getUserId();
		dto.setLeaderID(userId);
		return Map.of("result", ts.addTeam(dto));
	}
	
	// 팀 이름 중복 확인
	@GetMapping("/teamName/{teamName}")
	public Map<String, String> getTeamName(@PathVariable("teamName") String teamName){
		String name = ts.getTeamName(teamName);
		if(name==null)
			name = " ";
		return Map.of("result",name);
	}
	
	// 팀장 확인
	@GetMapping("/leader/{teamSeq}")
	public Map<String, String> getTeamLeader(@PathVariable("teamSeq") int teamSeq){
		String id = ts.getTeamLeader(teamSeq);
		if(id == null)
			id = " ";
		return Map.of("result", id);
	}
	
	// 전체 순위
	@GetMapping("/rank")
	public Map<String, List<TeamDTO>> getTeamRankList(){
		return Map.of("result", ts.getTeamRankList());
	}

	// 전체 순위 - 검색
	@GetMapping("/rank/{search}")
	public Map<String, List<TeamDTO>> getTeamRankListBySearch(@PathVariable("search") String search){
		return Map.of("result", ts.getSearchTeamRankList(search));
	}
	
	// 가입 가능 팀 조회
	@GetMapping("/possJoin")
	public Map<String, List<TeamDTO>> getPossibleJoinTeam(@SessionAttribute(name = "loginUser", required = false) UserDTO user){
		String userId = user.getUserId();
		return Map.of("result", ts.gettPossibleJoinTeamByUser(userId));
	}
	
	// 가입 가능 팀 조회 - 검색
	@GetMapping("/possJoin/{search}")
	public Map<String, List<TeamDTO>> getSearchPossibleJoinTeam(@PathVariable("search") String search, @SessionAttribute(name = "loginUser", required = false) UserDTO user){
		String userId = user.getUserId();
		return Map.of("result", ts.getSearchPossibleTeam(search, userId));
	}
	
	// 가입 신청 된 목록
	@GetMapping("/joinApply")
	public Map<String, List<TeamDTO>> getJoin(@SessionAttribute(name = "loginUser", required = false) UserDTO user){
		String userId = user.getUserId();
		return Map.of("result", ts.getApplyTeamList(userId));
	}
	
	@GetMapping("/teamSchedule/{teamSeq}")
	public Map<String, TeamScheduleDTO> getSchedule(@PathVariable("teamSeq") int teamSeq){
		
		TeamScheduleDTO dto = new TeamScheduleDTO();
		if(ts.getTeamSchedule(teamSeq) != null) {
			dto = ts.getTeamSchedule(teamSeq);
		}
		return Map.of("result", dto);
	}
	
	// 팀x - 가입신청 취소하기
	@DeleteMapping("/cancel/{teamSeq}")
	public Map<String, Boolean> deleteApply(@SessionAttribute(name = "loginUser", required = false) UserDTO user, @PathVariable("teamSeq") int teamSeq){
		String userId = user.getUserId();
		return Map.of("result", ts.removeApply(userId, teamSeq));
	}
	
	// 팀장 - 가입 거절
	@DeleteMapping("/cancelmember/{userId}/{teamSeq}")
	public Map<String, Boolean> deleteApply(@PathVariable("userId") String userId, @PathVariable("teamSeq") int teamSeq){
		return Map.of("result", ts.removeApply(userId, teamSeq));
	}
	
	// 팀 정보 불러오기
	@GetMapping("/teamInfo/{teamSeq}")
	public Map<String, TeamDTO> getTeamInfo(@PathVariable("teamSeq") int teamSeq){
		return Map.of("result", ts.getTeamInfoByModal(teamSeq));
	}
	
	// 팀 가입 신청하기
	@PostMapping("/applyTeam/{teamSeq}")
	public Map<String, Boolean> addTeamApply(@SessionAttribute(name = "loginUser", required = false) UserDTO user, @PathVariable("teamSeq") int teamSeq){
		String userId = user.getUserId();
		return Map.of("result", ts.addTeamApply(userId, teamSeq));
	}
	
	// 내 팀 정보
	@GetMapping("/myTeam")
	public Map<String, TeamDTO> getMyTeamInfo(@SessionAttribute(name = "loginUser", required = false) UserDTO user){
		String userId = user.getUserId();
		return Map.of("result", ts.getMyTeamInfo(userId));
	}

	// 내 팀원 정보
	@GetMapping("/myTeamMember/{teamSeq}")
	public Map<String, List<TeamMemberDTO>> getTeamMemberList(@PathVariable("teamSeq") int teamSeq){
		return Map.of("result", ts.getTeamMemberList(teamSeq));
	}
	
	// 팀 나가기 ********************
	@PutMapping("/updateStatus")
	public Map<String, Boolean> setMemberStatus(@SessionAttribute(name = "loginUser", required = false) UserDTO user){
		String userId = user.getUserId();
		return Map.of("result", ts.setTeamMemberStatus(userId));
	}
	
	// 팀원 강퇴하기
	@PutMapping("/updateDelete/{userId}")
	public Map<String, Boolean> setMemberDeleteStatus(@PathVariable("userId") String userId){
		return Map.of("result", ts.setTeamMemberDelete(userId));
	}
	
	// 팀 해체하기
	@PutMapping("/dismantle/{teamSeq}")
	public Map<String, Boolean> setMemberStatus(@PathVariable("teamSeq") int teamSeq){
		return Map.of("result", ts.setTeamDismantleStatus(teamSeq));
	}
	
	// 팀장 - 가입 승인 하기
	@PostMapping("/addMember/{userId}/{teamSeq}")
	public Map<String, Boolean> addTeamMember(@PathVariable("userId") String userId, @PathVariable("teamSeq") int teamSeq){
		return Map.of("result", ts.addTeamMember(userId, teamSeq));
	}
	
	// 팀장 - 모집 마감하기
	@PutMapping("/recruitment")
	public Map<String, Boolean> setRecruitment(@SessionAttribute(name = "loginUser", required = false) UserDTO user){
		String userId = user.getUserId();
		return Map.of("result", ts.setTeamRecruitment(userId));
	}
	
	// 팀장 - 추가 모집하기
	@PutMapping("/recruitmentTrue")
	public Map<String, Boolean> setRecruitment(@SessionAttribute(name = "loginUser", required = false) UserDTO user, @RequestBody TeamDTO dto){
		String userId = user.getUserId();
		dto.setLeaderID(userId);
		return Map.of("result", ts.setTeamInfo(dto));
	}
}
