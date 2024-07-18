package com.kosta.project.controller;

import com.kosta.project.dto.Manager.ManagerDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.dto.TeamDTO;
import com.kosta.project.service.FastMatchingService;
import com.kosta.project.service.MainPageService;
import com.kosta.project.service.ManagerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class WebController {
	private final ManagerService ms;
	
	@GetMapping("/loginPage")
	String loginPage() {
		return "managerLogin";
	}
	
	@GetMapping("/joinPage")
	String joinPage() {
		return "managerJoin";
	}
	
	@GetMapping("/main")
	String mainPage() {
		return "mainManager";
	}
}
