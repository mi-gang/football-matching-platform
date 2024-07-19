package com.kosta.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import com.kosta.project.domain.Field;
import com.kosta.project.dto.AddMatchingDataDTO;
import com.kosta.project.dto.FieldsDTO;
import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.dto.Manager.ManagerDTO;
import com.kosta.project.service.ManagerService;
import com.kosta.project.service.MatchingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequiredArgsConstructor
public class ManagerRestController {
	private final ManagerService ms;
	
	@PostMapping("/login")
	Map<String, Boolean> login(@RequestBody ManagerDTO mDTO, HttpServletRequest request) {
		boolean result = ms.getLoginResult(mDTO.getManagerId(), mDTO.getPassword());

		if(result == true) {
			HttpSession session = request.getSession();
			session.setAttribute("managerId", mDTO.getManagerId());
			return Map.of("result", result);
		}
		
		return Map.of("result", result);
	}
	
	@PutMapping("/fieldStatus/{fieldSeq}")
	Map<String, String> updateFieldStatus(@PathVariable Long fieldSeq){
		ms.updateFieldStatus(fieldSeq);
		return Map.of("result", "성공");
	}
}