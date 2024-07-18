package com.kosta.project.controller;

import java.util.List;

import com.kosta.project.dto.UserMatchingInfoDTO;
import com.kosta.project.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.dto.TeamDTO;
import com.kosta.project.service.FastMatchingService;
import com.kosta.project.service.MainPageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class WebController {
	@GetMapping("/mainManager")
	String mainManager() {
		return "mainManager";
	}
}
