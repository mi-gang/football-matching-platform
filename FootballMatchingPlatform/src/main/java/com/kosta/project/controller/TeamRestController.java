package com.kosta.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import com.kosta.project.dto.TeamDTO;
import com.kosta.project.dto.TeamMemberDTO;
import com.kosta.project.service.TeamService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class TeamRestController {

	private final TeamService ts;
	
	// 팀 유무 확인
	@GetMapping("/team/isTeam/{userId}")
	public Map<String, String> isTeam(@PathVariable("userId") String userId){
		String id = ts.isTeam(userId);
		if(id == null)
			id=" ";
		return Map.of("result", id);
	}
	
	// 팀장 확인
	@GetMapping("/team/leader/{teamSeq}")
	public Map<String, String> getTeamLeader(@PathVariable("teamSeq") int teamSeq){
		String id = ts.getTeamLeader(teamSeq);
		if(id == null)
			id = " ";
		return Map.of("result", id);
	}
	
	// 전체 순위
	@GetMapping("/team/rank")
	public Map<String, List<TeamDTO>> getTeamRankList(){
		return Map.of("result", ts.getTeamRankList());
	}

	// 가입 가능 팀 조회
	@GetMapping("/team/possJoin/{userId}")
	public Map<String, List<TeamDTO>> getPossibleJoinTeam(@PathVariable("userId") String userId){
		return Map.of("result", ts.gettPossibleJoinTeamByUser(userId));
	}
	
	// 팀 정보 불러오기
	@GetMapping("/team/teamInfo/{teamSeq}")
	public Map<String, TeamDTO> getTeamInfo(@PathVariable("teamSeq") int teamSeq){
		return Map.of("result", ts.getTeamInfoByModal(teamSeq));
	}
	
	// 팀 가입 신청하기
	@PostMapping("/team/applyTeam/{teamSeq}")
	public Map<String, Boolean> addTeamApply(@PathVariable("teamSeq") int teamSeq){
		String id = "user001";	// session에 담아올 ID
		return Map.of("result", ts.addTeamApply(id, teamSeq));
	}
	
	// 내 팀 정보
	@GetMapping("/team/myTeam/{userId}")
	public Map<String, TeamDTO> getMyTeamInfo(@PathVariable("userId") String userId){
		return Map.of("result", ts.getMyTeamInfo(userId));
	}

	// 내 팀원 정보
	@GetMapping("/team/myTeamMember/{teamSeq}")
	public Map<String, List<TeamMemberDTO>> getTeamMemberList(@PathVariable("teamSeq") int teamSeq){
		return Map.of("result", ts.getTeamMemberList(teamSeq));
	}
	
	// 팀 나가기
	@PutMapping("/team/updateStatus/{userId}")
	public Map<String, Boolean> setMemberStatus(@PathVariable("userId") String userId){
		return Map.of("result", ts.setTeamMemberStatus(userId));
	}
	
	// 팀원 강퇴하기
	@PutMapping("/team/updateDelete/{userId}")
	public Map<String, Boolean> setMemberDeleteStatus(@PathVariable("userId") String userId){
		return Map.of("result", ts.setTeamMemberDelete(userId));
	}
}
