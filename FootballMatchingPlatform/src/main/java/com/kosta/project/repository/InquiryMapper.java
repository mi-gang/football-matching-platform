package com.kosta.project.repository;
import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.InquiryDTO;

import java.util.List;

@Mapper
public interface InquiryMapper {
	
	// 내 문의 내역 최신 2개 불러오기
	List<InquiryDTO> selectTwoInquiry(String userId);
		
	// 내 문의 내역 전부 불러오기
	List<InquiryDTO> selectAllInquiry(String userId);
	
	// 선택한 문의 내역 상세 보기
	InquiryDTO selectDetailInfoByInquirySeq(int inquirySeq);
	
	// 문의 내역 추기하기
	void insertInquiry(InquiryDTO inquiryDTO);
	
}
