package com.kosta.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import com.kosta.project.dto.TeamDTO;
import com.kosta.project.dto.TeamMemberDTO;
import com.kosta.project.service.TeamService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamRestController {

	private final TeamService ts;
	
	// 팀 유무 확인
	@GetMapping("/isTeam")
	public Map<String, String> isTeam(){
		String id = ts.isTeam("user001");
		if(id == null)
			id=" ";
		return Map.of("result", id);
	}
	
	//팀 생성하기
	@PostMapping("/createTeam")
	public Map<String, Boolean> addTeam(@RequestBody TeamDTO dto){
		String userId = "user001";
		dto.setLeaderID(userId);
		// 
		return Map.of("result", ts.addTeam(dto));
	}
	
	// 팀장 확인
	@GetMapping("/leader/{teamSeq}")
	public Map<String, String> getTeamLeader(@PathVariable("teamSeq") int teamSeq){
		String id = ts.getTeamLeader(teamSeq);
		if(id == null)
			id = " ";
		return Map.of("result", id);
	}
	
	// 전체 순위
	@GetMapping("/rank")
	public Map<String, List<TeamDTO>> getTeamRankList(){
		return Map.of("result", ts.getTeamRankList());
	}

	// 가입 가능 팀 조회
	@GetMapping("/possJoin")
	public Map<String, List<TeamDTO>> getPossibleJoinTeam(){
		String userId = "user001";
		return Map.of("result", ts.gettPossibleJoinTeamByUser(userId));
	}
	
	// 가입 신청 된 목록
	@GetMapping("/joinApply")
	public Map<String, List<TeamDTO>> getJoin(){
		String userId = "user001";
		return Map.of("result", ts.getApplyTeamList(userId));
	}
	
	// 팀x - 가입신청 취소하기
	@DeleteMapping("/cancel/{teamSeq}")
	public Map<String, Boolean> deleteApply(@PathVariable("teamSeq") int teamSeq){
		String userId = "user001";	// session
		return Map.of("result", ts.removeApply(userId, teamSeq));
	}
	
	// 팀장 - 가입 거절
	@DeleteMapping("/cancelmember/{userId}/{teamSeq}")
	public Map<String, Boolean> deleteApply(@PathVariable("userId") String userId, @PathVariable("teamSeq") int teamSeq){
		return Map.of("result", ts.removeApply(userId, teamSeq));
	}
	
	// 팀 정보 불러오기
	@GetMapping("/teamInfo/{teamSeq}")
	public Map<String, TeamDTO> getTeamInfo(@PathVariable("teamSeq") int teamSeq){
		return Map.of("result", ts.getTeamInfoByModal(teamSeq));
	}
	
	// 팀 가입 신청하기
	@PostMapping("/applyTeam/{teamSeq}")
	public Map<String, Boolean> addTeamApply(@PathVariable("teamSeq") int teamSeq){
		String id = "user001";	// session에 담아올 ID
		return Map.of("result", ts.addTeamApply(id, teamSeq));
	}
	
	// 내 팀 정보
	@GetMapping("/myTeam")
	public Map<String, TeamDTO> getMyTeamInfo(){
		String userId = "user001";
		return Map.of("result", ts.getMyTeamInfo(userId));
	}

	// 내 팀원 정보
	@GetMapping("/myTeamMember/{teamSeq}")
	public Map<String, List<TeamMemberDTO>> getTeamMemberList(@PathVariable("teamSeq") int teamSeq){
		return Map.of("result", ts.getTeamMemberList(teamSeq));
	}
	
	// 팀 나가기
	@PutMapping("/updateStatus/{userId}")
	public Map<String, Boolean> setMemberStatus(@PathVariable("userId") String userId){
		return Map.of("result", ts.setTeamMemberStatus(userId));
	}
	
	// 팀원 강퇴하기
	@PutMapping("/updateDelete/{userId}")
	public Map<String, Boolean> setMemberDeleteStatus(@PathVariable("userId") String userId){
		return Map.of("result", ts.setTeamMemberDelete(userId));
	}
	
	// 팀 해체하기
	@PutMapping("/dismantle/{teamSeq}")
	public Map<String, Boolean> setMemberStatus(@PathVariable("teamSeq") int teamSeq){
		return Map.of("result", ts.setTeamDismantleStatus(teamSeq));
	}
	
	// 팀장 - 가입 승인 하기
	@PostMapping("/addMember/{userId}/{teamSeq}")
	public Map<String, Boolean> addTeamMember(@PathVariable("userId") String userId, @PathVariable("teamSeq") int teamSeq){
		return Map.of("result", ts.addTeamMember(userId, teamSeq));
	}
	
	// 팀장 - 모집 마감하기
	@PutMapping("/recruitment")
	public Map<String, Boolean> setRecruitment(){
		String userId = "user001";
		return Map.of("result", ts.setTeamRecruitment(userId));
	}
	
	// 팀장 - 추가 모집하기
	@PutMapping("/recruitmentTrue")
	public Map<String, Boolean> setRecruitment(@RequestBody TeamDTO dto){
		//ts.setTeamInfo(dto)
		String userId = "user001";
		dto.setLeaderID(userId);
		return Map.of("result", ts.setTeamInfo(dto));
	}
}
