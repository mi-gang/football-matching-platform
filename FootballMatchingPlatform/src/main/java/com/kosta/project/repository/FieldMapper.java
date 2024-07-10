package com.kosta.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.FieldDTO;

@Mapper
public interface FieldMapper {
	List<FieldDTO> selectFieldList();
//	List<FieldDTO> selectFieldListByFieldName(String fieldName);
//	FieldDTO selectFieldListByRegion(String region);
//	FieldDTO selectFieldListByFieldNameAndRegion(FieldDTO dto);
//	FieldDTO selectField(int fieldSeq);
}
