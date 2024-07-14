package com.kosta.project.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.project.dto.FieldsDTO;
import com.kosta.project.service.MatchingService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



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

	@GetMapping("/matching/fieldInfo/{fieldSeq}")
	public Map<String, FieldsDTO> getFieldInfo(@PathVariable int fieldSeq) {
		FieldsDTO fDTO = ms.getFieldInfo(fieldSeq);
		return Map.of("result", fDTO);
	}
	
}