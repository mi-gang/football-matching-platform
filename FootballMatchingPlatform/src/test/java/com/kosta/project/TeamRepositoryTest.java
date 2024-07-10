package com.kosta.project;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kosta.project.dto.TeamDTO;
import com.kosta.project.repository.TeamMapper;

import lombok.Builder;

@SpringBootTest
class TeamRepositoryTest{

	@Autowired
	TeamMapper teamMapper;
	
	//@Test
	void selectTeamRankList() {		// 팀 전체 순위
		System.out.println(teamMapper.selectTeamRankList());
	}
	
	//@Test
	void selectPossibleJoinTeam() {		// 팀 가입 가능 순위
		System.out.println(teamMapper.selectPossibleJoinTeam());
	}
	
	//@Test
	void insertTeam() {		// 팀 생성하기
		TeamDTO dto = TeamDTO.builder()
				.teamName("풋킹왕짱")
				.hometown("서울 금천구")
				.weekType("주말")
				.weekTime("주 2회")
				.hopeTime("08시-12시")
				.content("남자만 받아요22")
				.leaderID("chul01")
				.build();
		
		if(teamMapper.insertTeam(dto)) {
			String userId = dto.getLeaderID();
			int teamSeq = teamMapper.selectTeamSeq(dto.getTeamName());
			teamMapper.insertTeamMember(userId, teamSeq);
		}
		
	}
	
	//@Test
	void insertTeamApply() {	// 팀 가입신청
		int teamSeq = 3;
		String userId = "chul02";
		System.out.println(teamMapper.insertTeamApply(userId, teamSeq));
	}
	
	//@Test
	void deleteTeamApply() {	// 팀 신청 취소
		String userId = "user003";
		int teamSeq = 3;
		System.out.println(teamMapper.deleteApplyByTeamSeq(userId, teamSeq));
	}
	
	//@Test
	void selectApplyTeamList() {	// 신청 팀
		System.out.println(teamMapper.selectApplyTeamList("chul02"));
	}
	
	//@Test
	void selectTeamName() {	// 팀 중복체크
		System.out.println(teamMapper.selectTeamName("풋킹왕"));
	}
	
	//@Test
	void selectTeamInfoByModal() {		// 팀 정보 확인
		System.out.println(teamMapper.selectTeamInfoByModal(3));
		//System.out.println(teamMapper.selectTeamMemberTierAndCount(3));
		List<Map<String, Integer>> map = teamMapper.selectTeamMemberTierAndCount(3);
		for(int i=0; i<map.size(); i++) {
			System.out.println(map.get(i));
		}
	}
	
	//@Test
	void insertTeamMember() {		// 팀 가입 완료
		System.out.println(teamMapper.updateApplyTeamMemberStatus("user001"));
		System.out.println(teamMapper.insertTeamMember("user001", 3));
	}
	
	//@Test
	void selectTeamInfo2(){		// 팀 정보 확인
		System.out.println(teamMapper.selectTeamInfo("user001"));

	}
}
