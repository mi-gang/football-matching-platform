package com.kosta.project.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.FieldDTO;
import com.kosta.project.repository.FieldMapper;

@SpringBootTest
public class FieldMapperTest {
	@Autowired
	FieldMapper fieldMapper;
	
	@Test
	void getFieldListTest() {
		System.out.println(fieldMapper.selectFieldList());
	}
//	@Test
//	void getFieldListNameTest() {
//		System.out.println(fieldMapper.selectFieldListByFieldName("구장"));
//	}
//	@Test
//	void getFieldListRegionTest() {
//		System.out.println(fieldMapper.selectFieldListByRegion("서울 금천구"));
//	}
//	@Test
//	void getFieldListNameRegionTest() {
//		System.out.println(fieldMapper.selectFieldListByFieldNameAndRegion(FieldDTO.builder().fieldName("구장").fieldAddress("서울").build()));
//	}
//	@Test
//	void getFieldTest() {
//		System.out.println(fieldMapper.selectField(4));
//	}
}
