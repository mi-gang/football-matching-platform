package com.kosta.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.repository.UsersMapper;

@SpringBootTest
public class UserMapperTest {
	@Autowired
	UsersMapper um;
	
	//@Test
	void selectUserStatusTest() {
		System.out.println(um.selectUserStatus("chul01"));
	}
	
	@Test
	void selectSuspenedTime() {
		System.out.println(um.selectSuspenedTime("chul01"));
	}
}
