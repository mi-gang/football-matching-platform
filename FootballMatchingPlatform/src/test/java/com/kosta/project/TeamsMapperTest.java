package com.kosta.project;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.repository.FieldsMapper;
import com.kosta.project.repository.TeamsMapper;


@SpringBootTest
public class TeamsMapperTest {
	@Autowired
	TeamsMapper tm;
	
	//@Test
	void selectTeamSeqTest() {
		System.out.println(tm.selectTeamSeq("chul01"));
	}
	
	//@Test
	void selectTeamMemberCountTest() {
		System.out.println(tm.selectTeamMemberCount("chul01"));
	}
	
	@Test
	void selectTeamMemberIdsTest() {
		ArrayList<String> memberIds = new ArrayList<String>();
		memberIds = (ArrayList<String>) tm.selectTeamMemberIds("chul01");
		System.out.println(memberIds);
	}
}
