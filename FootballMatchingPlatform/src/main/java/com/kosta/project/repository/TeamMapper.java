package com.kosta.project.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.ApplyDTO;
import com.kosta.project.dto.MemberCountDTO;
import com.kosta.project.dto.TeamDTO;
import com.kosta.project.dto.TeamMemberDTO;
import com.kosta.project.dto.TeamScheduleDTO;

@Mapper
public interface TeamMapper {
	
	String isTeam(String userId);
	String selectTeamLeader(int teamSeq); 
	
	List<TeamDTO> selectTeamRankList();
	List<TeamDTO> selectSearchTeamRankList(String search);
	List<TeamDTO> selectPossibleJoinTeamByUser(String userId);
	
	List<TeamDTO> selectPossibleJoinTeam();
	List<TeamDTO> selectSearchPossibleTeam(String search, String userId);
	Set<TeamDTO> selectFilterPossibleTeam(String location, String gu, String userId);
	
	TeamScheduleDTO selectTeamschedule(int teamSeq);
	String selectRival(int teamSeq, int matchingSeq);
	
	// 팀생성	
	String selectTeamName(String teamName);	// 팀 이름 중복확인
	boolean insertTeam(TeamDTO dto);
	boolean insertTeamMember(String userId, int teamSeq);	// 팀 멤버 추가
	
	boolean insertTeamApply(String userId, int teamSeq);	// 팀 가입 신청
	boolean deleteApply(String userId, int teamSeq); 	// 가입신청 취소
	
	List<TeamDTO> selectApplyTeamList(String userId); //가입 신청된 목록
	
	TeamDTO selectTeamInfoByModal(int teamSeq);		// 모달창 팀 정보
	List<MemberCountDTO> selectTeamMemberTierAndCount(int teamSeq); // 모달 팀원 정보
	
	boolean updateApplyTeamMemberStatus(String userId);	// 신청 상태 변경 - 팀 허락
	
	TeamDTO selectTeamInfo(String userId);
	
	boolean updateTeamMemberStatus(String userId);	// 팀 나가기
	boolean updateTeamMemberDelete(String userId);	// 팀원 추방하기
	
	boolean updateTeamInfo(TeamDTO dto);	// 추가모집
	
	// 추가모집 마감
	boolean updateApplyTeamStatus(String userId);
	boolean updateTeamRecruitmentStatusByLeader(String userId);
	boolean updateTeamRecruitmentStatus(int teamSeq);
	

	List<TeamMemberDTO> selectTeamMemberList(int teamSeq); 	// 팀원 목록
	
	List<ApplyDTO> selectApplyList(String userId);	// 팀장- 가입 신청 목록
	
	boolean updateTeamDismantleStatus(int teamSeq);
	
	Integer selectTeamSeq(String userId);
	String selectTeamNameById(String userId);
	String selectTeamTier(String userId);
	int selectTeamMemberCount(String userId);
	int selectTeamMemberCNT(int teamSeq);
	
	List<String> selectTeamMemberIds(String userId);
	
	// 내 팀 정보 불러오기(마이페이지)
	TeamDTO selectTeamInfoByUserId(String userId);

}
