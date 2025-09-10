package com.kosta.project.repository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.repository.TeamMapper;


@SpringBootTest
public class TeamsMapperTest {
	@Autowired
	TeamMapper tm;
	
	//@Test
	void selectTeamSeqTest() {
		System.out.println(tm.selectTeamSeq("chul01"));
	}
	
	//@Test
	void selectTeamMemberCountTest() {
		System.out.println(tm.selectTeamMemberCount("user002"));
	}
	
	@Test
	void selectTeamMemberIdsTest() {
		ArrayList<String> memberIds = new ArrayList<String>();
		memberIds = (ArrayList<String>) tm.selectTeamMemberIds("user002");
		System.out.println(memberIds);
	}
}
