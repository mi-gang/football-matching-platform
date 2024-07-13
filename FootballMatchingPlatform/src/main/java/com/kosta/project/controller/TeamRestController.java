package com.kosta.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// 전체 순위
	@GetMapping("/team/rank")
	public Map<String, List<TeamDTO>> getTeamRankList(){
		return Map.of("result", ts.getTeamRankList());
	}

	// 가입 가능 팀 조회
	@GetMapping("/team/possJoin")
	public Map<String, List<TeamDTO>> getPossibleJoinTeam(){
		return Map.of("result", ts.getPossibleJoinTeam());
	}
	
	// 내 팀 정보
	@GetMapping("/team/myTeam/{userId}")
	public Map<String, TeamDTO> getMyTeamInfo(@PathVariable("userId") String userId){
		return Map.of("result", ts.getMyTeamInfo(userId));
	}

	@GetMapping("/team/myTeamMember/{teamSeq}")
	public Map<String, List<TeamMemberDTO>> getTeamMemberList(@PathVariable("teamSeq") int teamSeq){
		return Map.of("result", ts.getTeamMemberList(teamSeq));
	}
}
