package com.kosta.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.project.dto.TeamDTO;
import com.kosta.project.service.TeamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TeamRestController {

	private final TeamService ts;
	
	@GetMapping("/rank")
	public Map<String, List<TeamDTO>> getTeamRankList(){
		
		return Map.of("result", ts.getTeamRankList());
	}
	
}
