package com.kosta.project.repository;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.TeamDTO;
import com.kosta.project.repository.TeamMapper;

@SpringBootTest
class TeamRepositoryTest{

	@Autowired
	TeamMapper teamMapper;
	
	//@Test
	void selectTeamRankList() {		// 팀 전체 순위
		System.out.println(teamMapper.selectTeamRankList());
		System.out.println(teamMapper.selectSearchTeamRankList("풋"));
	}
	
	//@Test
	void selectPossibleJoinTeam() {		// 팀 가입 가능 순위
		System.out.println(teamMapper.selectPossibleJoinTeam());
		System.out.println(teamMapper.selectSearchPossibleTeam("풋"));
	}
	
	//@Test
	void insertTeam() {		// 팀 생성하기
		for(int i=0; i<5; i++) {
			TeamDTO dto = TeamDTO.builder()
					.teamName("풋킹왕짱"+i)
					.hometown("서울 금천구")
					.weekType("주말")
					.weekTime("주 2회")
					.hopeTime("08시-12시")
					.content("남자만 받아요22"+i)
					.leaderID("user0010"+i)
					.build();

			if(teamMapper.insertTeam(dto)) {
				String userId = dto.getLeaderID();
				int teamSeq = teamMapper.selectTeamSeq(dto.getTeamName());
				teamMapper.insertTeamMember(userId, teamSeq);
			}	
		}
	}
	
	//@Test
	void insertTeamApply() {	// 팀 가입신청
		int teamSeq = 4;
		String userId = "user00301";
		System.out.println(teamMapper.insertTeamApply(userId, teamSeq));
	}
	
	//@Test
	void deleteTeamApply() {	// 팀 신청 취소
		String userId = "user003";
		int teamSeq = 3;
		System.out.println(teamMapper.deleteApply(userId, teamSeq));
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
		System.out.println(teamMapper.selectTeamInfoByModal(1));
		//System.out.println(teamMapper.selectTeamMemberTierAndCount(3));
		List<Map<String, Integer>> map = teamMapper.selectTeamMemberTierAndCount(1);
		for(int i=0; i<map.size(); i++) {
			System.out.println(map.get(i));
		}
	}
	
	//@Test
	void insertTeamMember() {		// 팀 가입 완료
		System.out.println(teamMapper.updateApplyTeamMemberStatus("user00301"));
		System.out.println(teamMapper.insertTeamMember("user00301", 4));
		
		if(teamMapper.selectTeamMemberCNT(4) == 10) {
			//추가모집 마감 gogo
		}
		
	}
	
	//@Test
	void selectTeamInfo2(){		// 팀 정보 확인
		System.out.println(teamMapper.selectTeamInfo("user001"));

	}
	
	//@Test
	void updateTeamStatus(){		// 팀 나가기, 팀 추방하기
		System.out.println(teamMapper.updateTeamMemberStatus("user00300"));	// 나가기
		System.out.println(teamMapper.updateTeamMemberDelete("user00301"));	// 추방

	}
	
	//@Test
	void updateTeamInfo(){	
		TeamDTO dto = TeamDTO.builder()
				.hometown("서울 금천구")
				.weekType("주말")
				.weekTime("주 2회")
				.hopeTime("08시-12시")
				.content("남자만 받아요55")
				.possA(true)
				.possB(false)
				.possC(false)
				.possD(true)
				.leaderID("user00100")
				.build();
		
		System.out.println(teamMapper.updateTeamInfo(dto));
	}
	
	//@Test
	void updateTeamApplyStatus() {	// 추가 모집 마감
		System.out.println(teamMapper.updateApplyTeamStatus(""));
		System.out.println(teamMapper.updateTeamRecruitmentStatus(4));
	}
	
	//@Test
	void selectTeamMemberList() {
		System.out.println(teamMapper.selectTeamMemberList(1));
	}
	
	
	//@Test
	void selectTeamApplyList() {	// 팀장의 가입신청목록 조회
		System.out.println(teamMapper.selectApplyList("user00102"));
	}
	
	//@Test
	void selectTeamMemberCount() { // 팀 해체하기
		if(teamMapper.selectTeamMemberCNT(4) == 1) {
			System.out.println(teamMapper.updateTeamDismantleStatus(4));
		}
	}
	
}
