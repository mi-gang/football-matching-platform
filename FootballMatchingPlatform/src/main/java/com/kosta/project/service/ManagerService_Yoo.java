package com.kosta.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.project.dto.FieldDTO;
import com.kosta.project.dto.FieldInfoDTO;
import com.kosta.project.dto.MatchingDTO;
import com.kosta.project.dto.UserDTO;
import com.kosta.project.repository.FieldRepository;
import com.kosta.project.repository.MatchingMapper;
import com.kosta.project.repository.UserMapper;

import lombok.RequiredArgsConstructor;

//@Transactional
@Service
@RequiredArgsConstructor
public class ManagerService_Yoo {
	
	private final FieldRepository fr;
	
	// 구장 상세 정보
	public FieldInfoDTO getField(Long fieldSeq) {
		FieldInfoDTO dto = new FieldInfoDTO();
		
		dto.setF(fr.findById(fieldSeq).get());
		dto.setM(fr.findById(fieldSeq).get().getManager());
		
		return dto;
	}
	
}
