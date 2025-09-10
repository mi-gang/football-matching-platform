package com.kosta.project.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.MatchingDTO;
import com.kosta.project.dto.UserDTO;

@SpringBootTest
public class MatchingMapperTestJuho {
	
	@Autowired
	MatchingMapper mm;
	
	
	// 다가오는 경기 일정을 불러옵니다.
//	@Test
//	void selectMatchingAlreadyTest() {
//		String userId = "user00100";
//		List<MatchingDTO> ml = mm.selectMatchingAlready(userId);
//		for (MatchingDTO matchingDTO : ml) {
//            System.out.println("매칭 상태 : " + matchingDTO.getMatchingStatus()
//            					+ " 매칭 날짜 : " + matchingDTO.getMatchingDate()
//            					+ " 매칭 시간대 : " + matchingDTO.getMatchingTime()
//            					+ " 구장 위치 : " + matchingDTO.getFeildAddress()
//            					+ " 구장 위치 상세 : " + matchingDTO.getFeildAddressDetail()
//            					+ " 구장 명 : " + matchingDTO.getFeildName());
//        }
//	}
	
	
}
