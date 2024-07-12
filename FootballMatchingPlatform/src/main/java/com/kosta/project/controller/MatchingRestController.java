package com.kosta.project.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.project.service.MatchingService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class MatchingRestController {
	private final MatchingService ms;

	@GetMapping("/matching/isLeader/{userId}")
	Map<String, Boolean> getIsLeader(@PathVariable("userId") String userId){
		boolean isLeader = false;
		isLeader = ms.isLeader(userId);
		return Map.of("result", isLeader);
	}
	
	@GetMapping("/matching/isTeam/{userId}")
	Map<String, String> getIsTeam(@PathVariable("userId") String userId){
		String result = "";
		result = ms.isTeam(userId);
		return Map.of("result", result);
	}
}