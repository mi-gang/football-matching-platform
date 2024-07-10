package com.kosta.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.CloseTimeDTO;
import com.kosta.project.dto.FieldDTO;
import com.kosta.project.dto.FieldsDTO;

@Mapper
public interface FieldMapper {
	List<FieldDTO> selectFieldList();
	List<FieldDTO> selectFieldListByFieldName(String fieldName);
	FieldDTO selectFieldListByRegion(String fieldAddress);
	FieldDTO selectFieldListByFieldNameAndRegion(FieldDTO dto);
	FieldDTO selectField(int fieldSeq);
	
	FieldsDTO selectFieldInfo(int fieldSeq);
	List<Integer> selectFieldSeq();
	List<Integer> selectFieldSeqByRegion(String fieldAddress);
	List<Integer> selectCloseTime(CloseTimeDTO dto);
	FieldsDTO selectFastMatchingInfo(int matchingSeq);
}
