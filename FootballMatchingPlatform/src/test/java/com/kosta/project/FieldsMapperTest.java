package com.kosta.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.repository.FieldsMapper;


@SpringBootTest
public class FieldsMapperTest {
	@Autowired
	FieldsMapper fm;
	
	@Test
	void selectFieldInfoTest() {
		System.out.println(fm.selectFieldInfo(2));
	}
}
