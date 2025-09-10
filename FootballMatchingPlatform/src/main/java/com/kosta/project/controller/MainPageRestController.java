package com.kosta.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.project.dto.FieldDTO;
import com.kosta.project.service.MainPageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainPageRestController {

	private final MainPageService mps;
	
	
	
	
	@GetMapping("/api/field")
	public Map<String, List<FieldDTO>> getFieldList() {
		System.out.println("실행됨");
		return Map.of("result", mps.getFieldList());
	}
	
	
	
	
	@PostMapping("/api/field/fillter")
	public Map<String, List<FieldDTO>> getFieldListRegion(@RequestBody String fieldAddress){
		System.out.println("실행됨");
		
		String replacedString = fieldAddress.replace("\"", "");
		System.out.println(replacedString);
		return Map.of("result", mps.getFieldListRegion(replacedString));
	}
	
	
	
	
	@PostMapping("/api/field/search")
	public Map<String, List<FieldDTO>> getFieldListName(@RequestBody String search){
		System.out.println("실행됨");
		
		String replacedString = search.replace("\"", "");
		System.out.println(replacedString);
		return Map.of("result", mps.getFieldListName(replacedString));
	}
	
	
	
}
