package com.kosta.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.project.dto.FastMatchingConditionDTO;
import com.kosta.project.dto.FastMatchingDTO;
import com.kosta.project.dto.UserDTO;
import com.kosta.project.dto.UserMatchingInfoDTO;
import com.kosta.project.service.FastMatchingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
	public Map<String, String> isSuspendedUser(HttpServletRequest request) {
		System.out.println("실행됨");
		String result = "false";
		
		
		HttpSession session = request.getSession();

		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		
		String userId = user.getUserId();
		
		
		if(fms.isSuspendedUser(userId)) {
			result = "true";
		}
		System.out.println(result);
		
		return Map.of("result", result);
	}
	
	

	
	@GetMapping("/api/is/user/tier")
	public Map<String, String> isUserTier(HttpServletRequest request) {
		// 세션에서 유저 티어 가져오면 됨
		HttpSession session = request.getSession();

		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		
		//String userId = user.getUserId();
		
		String result = user.getUserTier();
		
		System.out.println(result);
		
		return Map.of("result", result);
	}
	
	
	// 세션값 필요
	@GetMapping("/api/fastmatchinglist")
	public Map<String, List<FastMatchingDTO>> getFastMatchingList(HttpServletRequest request){
		System.out.println("실행됨1");
		HttpSession session = request.getSession();

		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		
		String userId = user.getUserId();
		
		
		return Map.of("result", fms.getFastMatchingList(userId));
	}
	
	// 세션값 필요
	@GetMapping("/api/fastmatchinglist/big")
	public Map<String, List<FastMatchingDTO>> getFastMatchingListByBig(HttpServletRequest request){
		System.out.println("실행됨2");
		
		HttpSession session = request.getSession();

		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		
		String userId = user.getUserId();
		
		
		return Map.of("result", fms.getFastMatchingListByBig(userId));
	}
	
	// 세션값 필요
	@GetMapping("/api/fastmatchinglist/small")
	public Map<String, List<FastMatchingDTO>> getFastMatchingListBySmall(HttpServletRequest request){
		System.out.println("실행됨2");
		HttpSession session = request.getSession();
		
		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		
		String userId = user.getUserId();
		
		
		
		return Map.of("result", fms.getFastMatchingListBySmall(userId));
	}
	
	
	
	
	// 필터 Big// 세션값 필요
	@PostMapping("/api/fastmatchinglistb")
	public Map<String, List<FastMatchingDTO>> getFastMatchingListByBigAndDateAndRegionAndTier(@RequestBody FastMatchingConditionDTO fmcDTO, HttpServletRequest request) {
		System.out.println("실행됨");
		System.out.println(fmcDTO);

		HttpSession session = request.getSession();
		
		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		
		String userId = user.getUserId();
		
		
		fmcDTO.setUserId(userId);
		
		
		return Map.of("result", fms.getFastMatchingListByBigAndDateAndRegionAndTier(fmcDTO));
	}
	
	
	
	@PostMapping("/api/pay/if/low/ten")
	public Map<String, String> setPayStatusIfLowTen(@RequestBody UserMatchingInfoDTO umiDTO, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		
		String userId = user.getUserId();
		
		
		
		umiDTO.setUserId(userId);
		
		
		
		String result = "false";
		System.out.println("결제 controller 실행됨");
		
		
		boolean tryPay = fms.setTryPay(umiDTO);
		if(tryPay) {
			System.out.println("결제완료됨");
			result = "true";
		} else {
			System.out.println("결제가 안 됨");
		}

		
		return Map.of("result", result);
	}
	
	
	
	
	
	
	// 필터 Small// 세션값 필요
	@PostMapping("/api/fastmatchinglists")
	public Map<String, List<FastMatchingDTO>> getFastMatchingListBySmallAndDateAndRegionAndTier(@RequestBody FastMatchingConditionDTO fmcDTO, HttpServletRequest request) {

		HttpSession session = request.getSession();
		
		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		
		String userId = user.getUserId();
		
		fmcDTO.setUserId(userId);
		
		
		return Map.of("result", fms.getFastMatchingListBySmallAndDateAndRegionAndTier(fmcDTO));
	}
	
	
	
	
	
}
