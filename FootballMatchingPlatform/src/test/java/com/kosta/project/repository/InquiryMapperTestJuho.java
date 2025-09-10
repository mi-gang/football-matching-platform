package com.kosta.project.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.InquiryDTO;

@SpringBootTest
public class InquiryMapperTestJuho {
	
	@Autowired
	InquiryMapper im;
	
	// 내 문의 내역 최신 2개 불러오기
	@Test
	void selectTwoInquiryTest() {
		List<InquiryDTO> inquiryTwo = im.selectTwoInquiry("user00256");
		for(InquiryDTO inquiry : inquiryTwo) {
			System.out.println(inquiry.getTitle() + inquiry.getInquirySeq());
		}
	}
	
	
	// 내 문의 내역 전부 불러오기
	@Test
	void selectAllInquiryTest() {
		List<InquiryDTO> inquiryAll = im.selectAllInquiry("user00256");
		for(InquiryDTO inquiry : inquiryAll) {
			// getAnswerContentFlag()가 0이면 답변을 못 받은 상태이고, 1이면 답변을 받은 상태입니다.
			System.out.println(inquiry.getInquiryContent()+ inquiry.getTitle() + inquiry.getInquirySeq() + inquiry.getAnswerContentFlag());
		}
	}
	
	
	// 선택한 문의 내역 상세 보기
	@Test
	void selectDetailInfoByInquirySeqTest() {
		InquiryDTO inquiryDTO = im.selectDetailInfoByInquirySeq(2);
		
		System.out.println(inquiryDTO.getTitle() + inquiryDTO.getInquiryContent() + inquiryDTO.getAnswerContent());
	}
	
	
	// 문의 내역 추기하기
	@Test
	void insertInquiryTest() {
		//for(int i = 100; i < 500; i++) {
			InquiryDTO inquiryDTO = new InquiryDTO();
			inquiryDTO.setInquiryContent("매칭이 안 잡혀요 유저가 너무 없는 거 아닙니까? 광고 좀 하셔야 할 듯ㅇㅇ;;");
			inquiryDTO.setTitle("매칭이 안 잡혀요");
			//inquiryDTO.setUserId("user00" + i);
			inquiryDTO.setUserId("user00400");
			//for (int j = 0; j < 20; j++) {
				im.insertInquiry(inquiryDTO);
			//}	
		//}
	}
	
}
