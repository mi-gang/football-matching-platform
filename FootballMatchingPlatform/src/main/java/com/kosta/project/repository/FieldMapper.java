package com.kosta.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.CloseTimeDTO;
import com.kosta.project.dto.FieldDTO;
import com.kosta.project.dto.FieldsDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.dto.addMatchingListInfo;

@Mapper
public interface FieldMapper {
	List<FieldDTO> selectFieldList();
	List<FieldDTO> selectFieldListByFieldName(String fieldName);
	List<FieldDTO> selectFieldListByRegion(String fieldAddress);
	FieldDTO selectFieldListByFieldNameAndRegion(FieldDTO dto);
	FieldDTO selectField(int fieldSeq);
	
	// 전 구장 정보 불러오기 중 구장 정보 상세 보기
	FieldDTO selectFieldInfoDetail(int fieldSeq);
	
	FieldsDTO selectFieldInfo(int fieldSeq);
	addMatchingListInfo selectFieldAddressAndName(int fieldSeq);
	List<Integer> selectFieldSeq();
	List<Integer> selectFieldSeqByRegion(String fieldAddress);
	List<Integer> selectCloseTime(CloseTimeDTO dto);
	FieldsDTO selectFastMatchingInfo(int matchingSeq);
}
