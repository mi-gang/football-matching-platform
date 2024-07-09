package com.kosta.project.repository;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.FieldsDTO;

@Mapper
public interface FieldsMapper {
	FieldsDTO selectFieldInfo(int fieldSeq);
}
