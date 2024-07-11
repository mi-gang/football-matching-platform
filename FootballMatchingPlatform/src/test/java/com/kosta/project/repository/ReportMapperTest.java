package com.kosta.project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.ReportDTO;
import com.kosta.project.repository.ReportMapper;

@SpringBootTest
public class ReportMapperTest {
	@Autowired
	ReportMapper reportMapper;
	
	//@Test
	void insertReportTest() {
		boolean result = reportMapper.insertReport(new ReportDTO(0, "비매너 플레이", "경기 중 자꾸 상대팀 선수들에게 침을 뱉습니다.", 1, "user00100", "user00101"));
		System.out.println(result);
	}
	
	@Test
	void selectReportTest() {
		System.out.println(reportMapper.selectReport(new ReportDTO(0, "비매너 플레이", "경기 중 자꾸 상대팀 선수들에게 침을 뱉습니다.", 1, "user00100", "user00101")));
	}
	
}
