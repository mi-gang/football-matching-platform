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
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class FastMatchingRestController {

	private final FastMatchingService fms;
	
	@GetMapping("/api/is/suspended/user")
	public Map<String, String> isSuspendedUser(String userId) {
		System.out.println("실행됨");
		String result = "false";
		
		
		userId = "user010";
		
		if(fms.isSuspendedUser(userId)) {
			result = "true";
		}
		System.out.println(result);
		
		return Map.of("result", result);
	}
	
	

	
	@GetMapping("/api/is/user/tier")
	public Map<String, String> isUserTier(String userId) {
		// 세션에서 유저 티어 가져오면 됨
		String result = "D";
		
		
		return Map.of("result", result);
	}
	
	
	
	@GetMapping("/api/fastmatchinglist")
	public Map<String, List<FastMatchingDTO>> getFastMatchingList(){
		System.out.println("실행됨1");
		return Map.of("result", fms.getFastMatchingList());
	}
	
	
	@GetMapping("/api/fastmatchinglist/big")
	public Map<String, List<FastMatchingDTO>> getFastMatchingListByBig(){
		System.out.println("실행됨2");
		return Map.of("result", fms.getFastMatchingListByBig());
	}
	

	@GetMapping("/api/fastmatchinglist/small")
	public Map<String, List<FastMatchingDTO>> getFastMatchingListBySmall(){
		System.out.println("실행됨2");
		return Map.of("result", fms.getFastMatchingListBySmall());
	}
	
	
	
	
	// 필터 Big
	@PostMapping("/api/fastmatchinglistb")
	public Map<String, List<FastMatchingDTO>> getFastMatchingListByBigAndDateAndRegionAndTier(@RequestBody FastMatchingConditionDTO fmcDTO) {
		System.out.println("실행됨");
		System.out.println(fmcDTO);
		return Map.of("result", fms.getFastMatchingListByBigAndDateAndRegionAndTier(fmcDTO));
	}
	
	
	
	
	
	
	
	
	
	
	// 필터 Small
	@PostMapping("/api/fastmatchinglists")
	public Map<String, List<FastMatchingDTO>> getFastMatchingListBySmallAndDateAndRegionAndTier(@RequestBody FastMatchingConditionDTO fmcDTO) {
		return Map.of("result", fms.getFastMatchingListBySmallAndDateAndRegionAndTier(fmcDTO));
	}
	
	
	
	
	
}
