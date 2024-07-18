package com.kosta.project.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.FieldDTO;
import com.kosta.project.repository.FieldMapper;

@SpringBootTest
public class FieldRepositoryTest_hoon {
	@Autowired
	FieldRepository fr;
	
	@Test
	void findByManager_ManagerIdTest() {
		String managerId = "m0000";
		System.out.println(fr.findByManager_ManagerId(managerId));
	}
}
