package com.kosta.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kosta.project.dto.ApplyDTO;
import com.kosta.project.dto.TeamDTO;
import com.kosta.project.dto.TeamMemberDTO;
import com.kosta.project.repository.TeamMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamService {
	
	private final TeamMapper tm;
	
	
	//팀 유무
	public String isTeam(String userId) {
		return tm.isTeam(userId);
	}
	
	//팀장 확인
	public String getTeamLeader(int teamSeq) {
		return tm.selectTeamLeader(teamSeq);
	}
	
	// 팀 전체 순위
	public List<TeamDTO> getTeamRankList(){	
		List<TeamDTO> list = tm.selectTeamRankList();
		return list;
	}
	
	// 팀 전체 순위 - 검색
	public List<TeamDTO> getSearchTeamRankList(String search){
		List<TeamDTO> list = tm.selectSearchTeamRankList(search);
		return list;
	}
	
	// 가입 가능 팀 목록
	public List<TeamDTO> getPossibleJoinTeam(){
		List<TeamDTO> list = tm.selectPossibleJoinTeam();			
		return list;
	}
	
	public List<TeamDTO> gettPossibleJoinTeamByUser(String userId){
		List<TeamDTO> list = tm.selectPossibleJoinTeamByUser(userId);			
		return list;
	}
	
	// 가입 가능 팀 목록 - 검색
	public List<TeamDTO> getSearchPossibleTeam(String search){
		List<TeamDTO> list = tm.selectSearchPossibleTeam(search);
		return list;
	} 
	
	// 가입 가능 팀 정보 by 모달
	public TeamDTO getTeamInfoByModal(int teamSeq) {
		TeamDTO dto = tm.selectTeamInfoByModal(teamSeq);
		return dto;
	}
	
	// 가입 가능 팀원 정보 by 모달
	public List<Map<String, Integer>> getTeamMemberTierAndCount(int teamSeq){
		List<Map<String, Integer>> list = tm.selectTeamMemberTierAndCount(teamSeq);
		return list;
	}
	
	// 팀 가입 신청하기
	public boolean addTeamApply(String userId, int teamSeq) {
		return tm.insertTeamApply(userId, teamSeq);
	}
	
	// [팀장] 가입신청 목록 조회
	public List<ApplyDTO> getApplyList(String userId){
		List<ApplyDTO> list = null;
		
		list = tm.selectApplyList(userId);
		
		return list;
	}
	
	// 팀원 가입신청 승인하기 [팀장만 해당]
	public boolean addTeamMember(String userId, int teamSeq) {
		boolean res = false;
		
		if(tm.updateApplyTeamMemberStatus(userId)) {
			res = tm.insertTeamMember(userId, teamSeq);
		
			// 10명이 자동으로 채워지면, 모집마감
			if(tm.selectTeamMemberCNT(teamSeq) == 10) {
				res = tm.updateTeamRecruitmentStatus(teamSeq);
			}	
		}
		return res;
	}
	
	// 신청 취소하기 & 거절하기
	public boolean removeApply(String userId, int teamSeq) {
		return tm.deleteApply(userId, teamSeq);
	}
	
	// *********************** 가입 신청된 목록 조회 ***********************
	public List<TeamDTO> getApplyTeamList(String userId){
		List<TeamDTO> list = tm.selectApplyTeamList(userId);
		return list;
	}
	
	// 팀 이름 중복체크
	public String getTeamName(String teamName) {
		return tm.selectTeamName(teamName);
	}
	
	// 팀 생성하기
	public boolean addTeam(TeamDTO dto) {
		boolean res = false;
		
		if(tm.insertTeam(dto)) {
			String userId = dto.getLeaderID();
			int teamSeq = tm.selectTeamSeq(userId);
			tm.insertTeamMember(userId, teamSeq);
			
			res = true;
		}			
		return res;
	}
	
	// 내 팀 정보
	public TeamDTO getMyTeamInfo(String userId) {
		TeamDTO dto = tm.selectTeamInfo(userId);
		return dto;
	}
	
	// [팀장] 팀 해체하기
	public boolean setTeamDismantleStatus(String userId) {
		boolean res = false;
		if(tm.selectTeamMemberCount(userId) == 1) {
			int teamSeq = tm.selectTeamSeq(userId);
			res = tm.updateTeamDismantleStatus(teamSeq);
		}
		return res;
	}
	
	// 팀 나가기
	public boolean setTeamMemberStatus(String userId) {
		return tm.updateTeamMemberStatus(userId);
	}
	
	// [팀장] 팀원 강퇴하기
	public boolean setTeamMemberDelete(String userId) {
		return tm.updateTeamMemberDelete(userId);
	}
	
	// [팀장] 팀원 모집 마감하기
	public boolean setTeamRecruitment(String userId) {
		boolean res = false;
		if(tm.updateApplyTeamStatus(userId)) {
			res = tm.updateTeamRecruitmentStatusByLeader(userId);
		}
		return res;
	}
	
	// 팀 추가모집 하기
	public boolean setTeamInfo(TeamDTO dto) {
		return tm.updateTeamInfo(dto);
	}
	
	// 팀원 목록
	public List<TeamMemberDTO> getTeamMemberList(int teamSeq){
		List<TeamMemberDTO> list = tm.selectTeamMemberList(teamSeq);
		return list;
	}
}
