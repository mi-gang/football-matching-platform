package com.kosta.project.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.project.service.SystemManagerFieldService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FieldApplyRestController {

	private final SystemManagerFieldService smfs;
	
	// @PostMapping("/api/update/approve/date")
	// public Map<String, String> updateApprove(@PathVariable Long fieldSeq){
		
	// 	smfs.updateFieldStatus2(fieldSeq);
		
	// 	return Map.of("result", "성공");
	// }
	
}
