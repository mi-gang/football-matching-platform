package com.kosta.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.project.dto.AddMatchingDataDTO;
import com.kosta.project.dto.FieldsDTO;
import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingsDTO;
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
	public Map<String, Boolean> getIsLeader(@PathVariable("userId") String userId){
		boolean isLeader = false;
		isLeader = ms.isLeader(userId);
		return Map.of("result", isLeader);
	}
	
	@GetMapping("/matching/isTeam/{userId}")
	public Map<String, String> getIsTeam(@PathVariable("userId") String userId){
		String result = "";
		result = ms.isTeam(userId);
		return Map.of("result", result);
	}

	@GetMapping("/matching/fieldInfo/{fieldSeq}")
	public Map<String, FieldsDTO> getFieldInfo(@PathVariable int fieldSeq) {
		FieldsDTO fDTO = ms.getFieldInfo(fieldSeq);
		return Map.of("result", fDTO);
	}
	
	@GetMapping("/matchinglistByRegion")
	public Map<String, List<MatchingsDTO>> getMatchingListByRegion(String matchingDate, String matchingTime, String region){
		List<MatchingsDTO> mDTOList = null;
		MatchingConditionDTO mcDTO = MatchingConditionDTO.builder()
				.matchingDate(matchingDate)
				.matchingTime(matchingTime)
				.fieldAddress(region)
				.build();
		System.out.println(mcDTO);
		mDTOList = ms.getMatchingsListByRegion(mcDTO);
		System.out.println(mDTOList);
		return Map.of("result", mDTOList);
	}
	
	@PostMapping("/matching")
	public Map<String, Boolean> addMatching(@RequestBody AddMatchingDataDTO addDTO){
		boolean result = false;
		result = ms.addMatcings(addDTO);
		return Map.of("reuslt", result);
	}
	
	@GetMapping("/matchingAddResult")
	public Map<String, List<MatchingConditionDTO>> getMatchingAddResult(String addType, String userId){
		List<MatchingConditionDTO> mcDTOList = null;
		AddMatchingDataDTO addDTO = AddMatchingDataDTO.builder()
				.type(addType)
				.userId(userId)
				.build();
		mcDTOList = ms.getMatchingAddResult(addDTO);
		return Map.of("result", mcDTOList);
	}
}