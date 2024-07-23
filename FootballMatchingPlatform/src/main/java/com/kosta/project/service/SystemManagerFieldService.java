package com.kosta.project.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.kosta.project.domain.Field;
import com.kosta.project.repository.FieldRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SystemManagerFieldService {
	
	private final FieldRepository fr;
	
	// // 구장 승인 (승인 날짜 업데이트)
	// public void updateApprove(Long fieldSeq) {
	// 	Field field = fr.findById(fieldSeq).get();
	// 	Date date = new Date();
	// 	Instant instant = date.toInstant();
	// 	LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		
	// 	field.setFieldApprovalDate(localDate);
		
	// 	fr.save(field);
	// }
	
	
	// // 구장 승인 거절(field_status를 2로 변경
	// public void updateFieldStatus2(Long fieldSeq) {
	// 	Field field = fr.findById(fieldSeq).get();
		
	// 	int status = 2;
		
	// 	field.setFieldStatus(status);
		
	// 	fr.save(field);
	// }
	
	
}
