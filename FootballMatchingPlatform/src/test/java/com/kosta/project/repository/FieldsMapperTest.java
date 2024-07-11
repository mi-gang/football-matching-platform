package com.kosta.project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.CloseTimeDTO;
import com.kosta.project.repository.FieldMapper;


@SpringBootTest
public class FieldsMapperTest {
	@Autowired
	FieldMapper fm;
	
	//@Test
	void selectFieldInfoTest() {
		System.out.println(fm.selectFieldInfo(2));
	}
	
	//@Test
	void selectFieldSeqTest() {
		System.out.println(fm.selectFieldSeq());
	}
	
	//@Test
	void selectFieldSeqByRegionTest() {
		System.out.println(fm.selectFieldSeqByRegion("인천광역시 미추홀구"));
	}
	
	//@Test
	void selectCloseTimeTest() {
		CloseTimeDTO dto = CloseTimeDTO.builder()
				.closedDate("2024-07-10")
				.closedTime("12")
				.fieldSeq(3)
				.build();
		System.out.println(fm.selectCloseTime(dto));
	}
	
	@Test
	void selectFastMatchingInfoTest() {
		System.out.println(fm.selectFastMatchingInfo(1));
	}
}