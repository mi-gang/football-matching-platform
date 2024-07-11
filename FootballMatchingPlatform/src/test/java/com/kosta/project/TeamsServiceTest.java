package com.kosta.project;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.TeamDTO;
import com.kosta.project.repository.TeamMapper;
import com.kosta.project.service.TeamService;


@SpringBootTest
public class TeamsServiceTest {
	@Autowired
	TeamService ts;
	
	//@Test
	void getTeamRank() {
		System.out.println(ts.getTeamRankList());
	}
	
	//@Test
	void getPossJoinTeam() {
		System.out.println(ts.getPossibleJoinTeam());
	}

	//@Test
	void getSearchList() {
		System.out.println(ts.getSearchTeamRankList("뭐"));
		System.out.println(ts.getSearchPossibleTeam("뭐"));
	}
	
	//@Test
	void getTeamInfoByModal(){
		System.out.println(ts.getTeamInfoByModal(1));
	}
	
	//@Test
	void getTeamMemberTierAndCount() {
		System.out.println(ts.getTeamMemberTierAndCount(1));
	}
	
	//@Test
	void addTeamApply() { // 팀 신청하기
		for(int i=0; i<5; i++) {
			System.out.println(ts.addTeamApply("user0041"+i, 6));
		}

	}
	
	//@Test
	void removeApply() {
		System.out.println(ts.removeApply("user00401", 1));
	}
	
	//@Test
	void addTeam() {
		TeamDTO dto = TeamDTO.builder()
				.teamName("zl존풋살")
				.hometown("서울 금천구")
				.weekType("주말")
				.weekTime("주 2회")
				.hopeTime("08시-12시")
				.content("남자만 받아요22")
				.possA(false)
				.possB(false)
				.possC(true)
				.possD(false)
				.leaderID("user00150")
				.build();
		
		System.out.println(ts.addTeam(dto));
	}
	
	@Test
	void getApplyTeamList() {
		System.out.println(ts.getApplyTeamList("user00410"));
	}
	
	//@Test
	void getMyTeamInfo() {
		System.out.println(ts.getMyTeamInfo("user00100"));
	}
	
	//@Test
	void getTeamName() {
		System.out.println(ts.getTeamName("풋킹"));
	}
	
//	@Test
	void addTeamMember() {
		System.out.println(ts.addTeamMember("user00408", 5));
	}
	
	//@Test
	void setTeamMemberStatus() {
		System.out.println(ts.setTeamMemberStatus("user00407"));	// 팀 나가기
		System.out.println(ts.setTeamMemberDelete("user00408"));	// 팀원 강퇴
	}
	
	//@Test		해체하기
	void setTeamDismantleStatus(){
		System.out.println(ts.setTeamDismantleStatus("user999"));
	}
	
	//@Test		// 팀장 가입 목록
	void selectApplyList(){
		System.out.println(ts.getApplyList("user00102"));
	}
	
	//@Test [팀장-모집마감]
	void setTeamRecruitment() {
		System.out.println(ts.setTeamRecruitment("user00102"));
	}
	
	//@Test	//팀원 추가모집
	void setTeamInfo() {
		TeamDTO dto = TeamDTO.builder()
				.hometown("서울 양천구")
				.weekType("주중")
				.weekTime("주 4회")
				.hopeTime("16시-20시")
				.content("남녀무관 다들어오슈")
				.possA(false)
				.possB(false)
				.possC(true)
				.possD(false)
				.leaderID("user00200")
				.build();
		System.out.println(ts.setTeamInfo(dto));
	}
	
	//@Test
	void getTeamMemberList() {
		System.out.println(ts.getTeamMemberList(5));
	}
	
}
