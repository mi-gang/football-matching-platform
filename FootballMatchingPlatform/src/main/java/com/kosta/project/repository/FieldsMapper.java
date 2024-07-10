package com.kosta.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.CloseTimeDTO;
import com.kosta.project.dto.FieldsDTO;

@Mapper
public interface FieldsMapper {
	FieldsDTO selectFieldInfo(int fieldSeq);
	List<Integer> selectFieldSeq();
	List<Integer> selectFieldSeqByRegion(String fieldAddress);
	List<Integer> selectCloseTime(CloseTimeDTO dto);
	FieldsDTO selectFastMatchingInfo(int matchingSeq);
}
