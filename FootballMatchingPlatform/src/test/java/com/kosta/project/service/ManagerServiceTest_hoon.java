package com.kosta.project.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.FieldDTO;
import com.kosta.project.repository.FieldMapper;
import com.kosta.project.repository.FieldRepository;
import com.kosta.project.repository.ManagerRepository;

@SpringBootTest
public class ManagerServiceTest_hoon {
	@Autowired
	FieldRepository fr;
	@Autowired
	ManagerService ms;
	
	@Test
	void findByManagerIdTest() {
		String managerId = "m0000";
		String password = "m00001";
		System.out.println(ms.getLoginResult(managerId, password));
	}
}
