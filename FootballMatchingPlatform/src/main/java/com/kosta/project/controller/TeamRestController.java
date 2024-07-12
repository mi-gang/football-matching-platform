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
import com.kosta.project.service.TeamService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class TeamRestController {

	private final TeamService ts;
	
	@GetMapping("/team/isTeam/{userId}")
	public Map<String, String> isTeam(@PathVariable("userId") String userId){
		String id = ts.isTeam(userId);
		if(id == null)
			id=" ";
		return Map.of("result", id);
	}
	
	@GetMapping("/team/rank")
	public Map<String, List<TeamDTO>> getTeamRankList(){
		return Map.of("result", ts.getTeamRankList());
	}

	@GetMapping("/team/possJoin")
	public Map<String, List<TeamDTO>> getPossibleJoinTeam(){
		return Map.of("result", ts.getPossibleJoinTeam());
	}
	

}
