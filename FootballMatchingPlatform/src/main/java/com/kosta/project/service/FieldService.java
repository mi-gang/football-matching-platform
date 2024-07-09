package com.kosta.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.project.dto.FieldDTO;
import com.kosta.project.repository.FieldMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FieldService {
	private final FieldMapper fieldMapper;
	
//	public List<FieldDTO> getFieldList(){
//		return fieldMapper.selectFieldList();
//	}
	
//	public List<FieldDTO> getFieldListByFieldName(String fieldName){
//		return fieldMapper.selectFieldListByFieldName(fieldName);
//	}
}
