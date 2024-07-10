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
	void fieldsTest() {
		System.out.println(fieldMapper.selectFieldList());
	}
}
