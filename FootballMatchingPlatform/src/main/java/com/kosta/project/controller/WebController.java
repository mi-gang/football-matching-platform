package com.kosta.project.controller;

import com.kosta.project.dto.Manager.FieldDTO;
import com.kosta.project.dto.Manager.ManagerDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosta.project.domain.Field;
import com.kosta.project.dto.FieldInfoDTO;
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
	String mainPage(Model model, HttpServletRequest request) {
		String managerId = (String) request.getSession().getAttribute("managerId");
		List<Field> fieldList = ms.getFieldList(managerId);
		model.addAttribute("fieldList", fieldList);
		
		return "mainManager";
	}
	
	@GetMapping("/schedule")
	String schedule(Model model, HttpServletRequest request) {
		String managerId = (String) request.getSession().getAttribute("managerId");
		List<Field> fieldList = ms.getApprovedFieldList(managerId);
		List<FieldDTO> fieldDTOList = new ArrayList<FieldDTO>();
		for(int i=0; i<fieldList.size(); i++){
			FieldDTO fieldDTO = FieldDTO.builder()
			.fieldSeq(fieldList.get(i).getFieldSeq())
			.fieldName(fieldList.get(i).getFieldName())
			.fieldStatus(fieldList.get(i).getFieldStatus())
			.build();
			fieldDTOList.add(fieldDTO);
		}

		model.addAttribute("fieldList", fieldDTOList);
		return "scheduleManage";
	}
	
	@GetMapping("/fieldInfo/{fieldSeq}")
	String fieldInfo(Model model, @PathVariable int fieldSeq) {
		FieldInfoDTO fiDTO = ms.getField(fieldSeq);
		model.addAttribute("fieldInfo", fiDTO);
		return "fieldInfo";
	}
	
	@GetMapping("/addField")
	String addfield() {
		return "addField";
	}
	
	@GetMapping("/fieldApplyList")
	public String getFieldList() {
		return "fieldApplyList";
	}
	
	@GetMapping("/userList")
	public String getUserList() {
		return "userList";
	}
	
}
