package com.kosta.project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.repository.UserMapper;

@SpringBootTest
public class UserMapperTest {
	@Autowired
	UserMapper um;
	
	//@Test
	void selectUserStatusTest() {
		System.out.println(um.selectUserStatus("chul01"));
	}
	
	@Test
	void selectSuspenedTime() {
		System.out.println(um.selectSuspendedTime("chul01"));
	}
}
