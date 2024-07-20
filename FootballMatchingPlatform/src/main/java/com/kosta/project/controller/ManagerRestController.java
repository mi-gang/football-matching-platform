package com.kosta.project.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.project.domain.Field;
import com.kosta.project.domain.Field;
import com.kosta.project.dto.AddMatchingDataDTO;
import com.kosta.project.dto.FieldBoxDTO;
import com.kosta.project.dto.FieldDTO;
import com.kosta.project.dto.FieldsDTO;
import com.kosta.project.dto.ImageUploadDTO;
import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.dto.UserDTO;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;



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
	Map<String, String> updateFieldStatus(@PathVariable int fieldSeq){
		ms.updateFieldStatus(fieldSeq);
		return Map.of("result", "성공");
	}
	
	// 구장 등록
	@PostMapping("/addfield")
	public Map<String, Long> addField(@RequestBody Field dto, @SessionAttribute(name = "managerId", required = false) String managerId){		
		return Map.of("result", 1L);
	}
	
	// 이미지 등록
	@PostMapping("/addField/image")
	public Map<String, Boolean> addFieldByImage(@RequestPart("img") MultipartFile img, 
	@RequestPart("field") Field field, 
	@SessionAttribute(name = "managerId", required = false) String managerId){

		boolean res = ms.addFieldImg(img, field, managerId);

		return Map.of("result", res);
	}
}