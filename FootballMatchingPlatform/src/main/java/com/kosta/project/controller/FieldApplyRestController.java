package com.kosta.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.project.domain.Field;
import com.kosta.project.dto.FieldListForSystemManagerDTO;
import com.kosta.project.dto.FieldSearchSystemManagerDTO;
import com.kosta.project.service.SystemManagerFieldService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FieldApplyRestController {

	private final SystemManagerFieldService smfs;
	
	@PostMapping("/api/update/approve/date")
	public Map<String, String> updateApprove(@RequestBody int fieldSeq){
		//System.out.println("과연1~~" + fieldSeq);
		smfs.updateApprove(fieldSeq);
		
		return Map.of("result", "성공");
	}
	
	
	@PostMapping("/api/no/approve/field")
	public Map<String, String> noApprove(@RequestBody int fieldSeq) {
		//System.out.println("과연2~~" + fieldSeq);
		
		smfs.updateFieldStatus2(fieldSeq);
		
		return Map.of("result", "성공");
	}
	
	
	@GetMapping("/api/apply/list/field/default")
	public Map<String, List<FieldListForSystemManagerDTO>> applyListDefault() {
		return Map.of("result", smfs.getFieldList());
	}
	
	
	@PostMapping("/api/apply/list/field/address")
	public Map<String, List<FieldListForSystemManagerDTO>> applyListAddress(@RequestBody FieldSearchSystemManagerDTO fssm) {
		System.out.println("테스트 확인---------" + fssm.getFieldAddress());
		
		String newAddressStr1 = fssm.getFieldAddress().replace("시/도 선택", " ");
		
		String newAddressStr2 = newAddressStr1.replace(" 구/군 선택", "");
		
		System.out.println(newAddressStr2);
		
		return Map.of("result", smfs.getFieldListByAddress(newAddressStr2));
	}
	
	
	@PostMapping("/api/apply/list/field/address/name")
	public Map<String, List<FieldListForSystemManagerDTO>> applyListAddressName(@RequestBody FieldSearchSystemManagerDTO fssm) {
		//System.out.println(fssm.getFieldAddress());
		//System.out.println("테스트 확인 이름---------" + fssm.getFieldName());
		
		String newAddressStr1 = fssm.getFieldAddress().replace("시/도 선택", "");
		
		String newAddressStr2 = newAddressStr1.replace(" 구/군 선택", "");
		
	//	System.out.println("테스트 확인 주소---------" + newAddressStr2);
		return Map.of("result", smfs.getFieldListByAddressAndFieldName(newAddressStr2, fssm.getFieldName()));
	}
	
	
	
	
}
