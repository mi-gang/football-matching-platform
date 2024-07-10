package com.kosta.project;


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
//		System.out.println(fieldMapper.selectFieldListByRegion("서울 금천"));
//	}
//	@Test
//	void getFieldListNameRegionTest() {
//		System.out.println(fieldMapper.selectFieldListByFieldNameAndRegion("구장","서울 금천"));
//	}
//	@Test
//	void getFieldTest() {
//		System.out.println(fieldMapper.selectField(4));
//	}
}
