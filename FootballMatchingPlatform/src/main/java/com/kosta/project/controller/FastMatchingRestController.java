package com.kosta.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.project.dto.FastMatchingConditionDTO;
import com.kosta.project.dto.FastMatchingDTO;
import com.kosta.project.service.FastMatchingService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class FastMatchingRestController {

	private FastMatchingService fms;
	
	@PostMapping("/issuspendeduser")
	public Map<String, Boolean> isSuspendedUser(@RequestBody String userId) {
		//TODO: process POST request
		
		return Map.of("result", fms.isSuspendedUser(userId));
	}
	
	
	// 필터 Big
//	@PostMapping
//	public Map<String, List<FastMatchingDTO>> getFastMatchingListByBigAndDateAndRegionAndTier(@RequestBody FastMatchingConditionDTO fmcDTO) {
//		
//		//fms.getFastMatchingListBySmallAndDateAndRegionAndTier(fmcDTO)
//		//List<FastMatchingDTO> data = fms.getFastMatchingListBySmallAndDateAndRegionAndTier(fmcDTO)
//				
//		//if로 한 메서드에서 처리해도 될 거 같긴 함ㅇㅇ;;
//		
//		return Map.of("result", fms.getFastMatchingListByBigAndDateAndRegionAndTier(fmcDTO));
//	}
//	
//	
//	// 필터 Small
//	@PostMapping
//	public Map<String, List<FastMatchingDTO>> getFastMatchingListBySmallAndDateAndRegionAndTier(@RequestBody FastMatchingConditionDTO fmcDTO) {
//		return Map.of("result", fms.getFastMatchingListBySmallAndDateAndRegionAndTier(fmcDTO));
//	}
	
	
	
	
	
}
