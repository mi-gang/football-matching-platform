package com.kosta.project.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.FieldDTO;
import com.kosta.project.repository.FieldMapper;

@SpringBootTest
public class ManagerRepositoryTest_hoon {
	@Autowired
	FieldRepository fr;
	@Autowired
	ManagerRepository mr;
	
	//@Test
	void findByManagerIdAndPasswordTest() {
		String managerId = "m0000";
		String password = "m0000";
		System.out.println(mr.findByManagerIdAndPassword(managerId, password));
	}
	
	@Test
	void findByManagerIdTest() {
		String managerId = "m0000";
		System.out.println();
	}
}