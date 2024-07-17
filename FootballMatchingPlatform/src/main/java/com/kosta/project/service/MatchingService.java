package com.kosta.project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.kosta.project.dto.CloseTimeDTO;
import com.kosta.project.dto.FieldsDTO;
import com.kosta.project.dto.MatchingAddListsDTO;
import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingCountDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.dto.PlayerNumberDTO;
import com.kosta.project.dto.AddMatchingDataDTO;
import com.kosta.project.dto.addMatchingListInfo;
import com.kosta.project.dto.addMatchingsDTO;
import com.kosta.project.repository.FieldMapper;
import com.kosta.project.repository.MatchingMapper;
import com.kosta.project.repository.TeamMapper;
import com.kosta.project.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchingService {

	private final MatchingMapper mm;
	private final FieldMapper fm;
	private final UserMapper um;
	private final TeamMapper tm;

	public boolean isLeader(String userId) {
		boolean result = false;
		Integer teamSeq = tm.selectTeamSeq(userId);

		if(teamSeq != null) {
			result = true;
		}

		return result;
	}

	public String isTeam(String userId) {
		String msg = "true";
		int teamMemberCount = tm.selectTeamMemberCount(userId);

		if(teamMemberCount < 5) {
			return "팀원이 부족합니다.";
		}

		List<String> memberIds = tm.selectTeamMemberIds(userId);

		for(int i=0; i<memberIds.size(); i++) {
			String suspendedTime = um.selectSuspendedTime(memberIds.get(i));
			if(suspendedTime != null) {
				LocalDate suspendedDate = LocalDate.parse(suspendedTime);
				LocalDate now = LocalDate.now();
				if(now.isBefore(suspendedDate)) {
					msg = "정지된 팀원이 있습니다.";
				}
			}
		}
		return msg;
	}

	public List<MatchingsDTO> getMatchingsList(MatchingConditionDTO dto){
		String matchingTime = dto.getMatchingTime();
		String[] matchingTimes = matchingTime.split(" ");
		ArrayList<String> matchingTimeList = new ArrayList<String>(Arrays.asList(matchingTimes));
		List<MatchingsDTO> matchingListAllByTime = new ArrayList<MatchingsDTO>();
		List<MatchingsDTO> matchingListAll = new ArrayList<MatchingsDTO>();
		List<MatchingsDTO> matchingListByTime = new ArrayList<MatchingsDTO>();
		List<MatchingsDTO> matchingList = new ArrayList<MatchingsDTO>();
		String userId = dto.getUserId();
		for(int i=0; i<matchingTimeList.size(); i++) {
			dto.setMatchingTime(matchingTimeList.get(i));
			if(dto.getAddType().equals("개인")) {
				dto.setTeamSeq(0);
				matchingListByTime = mm.selectMatchingsList(dto);
				matchingListAllByTime = mm.selectMatchingsListAll(dto);
				System.out.println(matchingListByTime);
				System.out.println(matchingListAllByTime);
			}
			else if(dto.getAddType().equals("팀")) {
				dto.setTeamSeq(tm.selectTeamSeq(userId));
				dto.setUserId("null");
				matchingListByTime = mm.selectMatchingsList(dto);
				matchingListAllByTime = mm.selectMatchingsListAll(dto);
			}
			for(int j=0; j<matchingListByTime.size(); j++) {
				matchingList.add(matchingListByTime.get(j));
			}
			
			for(int j=0; j<matchingListAllByTime.size(); j++) {
				matchingListAll.add(matchingListAllByTime.get(j));
			}
		}

		// 입력값에 따른 매칭중인 매칭정보 ex) 24-07-10, 14시 입력

		// 이용 가능한 구장번호 가져오기
		List<Integer> possField = fm.selectFieldSeq();

		boolean ck = false;
		// timeList : 14
		for(int j=0; j<matchingTimes.length; j++) {
			String time = matchingTimes[j];

			//time : 14, 16
			for(int i=0; i<possField.size(); i++) {
				int fieldSeq = possField.get(i);

				for(int k=0; k < matchingListAll.size(); k++) {
					if(matchingListAll.get(k).getMatchingTime() == Integer.parseInt(time) && matchingListAll.get(k).getFieldSeq()==fieldSeq) {
						ck = true;
						break;
					}
				}

				if(ck == true) {
					ck=false;
					continue;
				}

				CloseTimeDTO ctDTO = CloseTimeDTO.builder()
						.closedDate(dto.getMatchingDate())
						.closedTime(time)
						.fieldSeq(fieldSeq)
						.build();
				if(!fm.selectCloseTime(ctDTO).isEmpty())
					continue;

				//추가해줄 DTOs
				addMatchingListInfo amlDTO = fm.selectFieldAddressAndName(fieldSeq);
				MatchingsDTO addDTO = MatchingsDTO.builder()
						.matchingDate(dto.getMatchingDate())
						.matchingTime(Integer.parseInt(time))
						.fieldAddress(amlDTO.getFieldAddress())
						.fieldName(amlDTO.getFieldName())
						.fieldSeq(amlDTO.getFieldSeq())
						.build();
				matchingList.add(addDTO);
			}
		}
		return matchingList;
	}

	public List<MatchingsDTO> getMatchingsListByRegion(MatchingConditionDTO dto){
		List<MatchingsDTO> resList = null;
		resList = getMatchingsList(dto);
		String matchingRegion = dto.getFieldAddress();
		String[] matchingRegions = matchingRegion.split(" ");
		ArrayList<String> matchingRegionList = new ArrayList<String>(Arrays.asList(matchingRegions));
		List<MatchingsDTO> matchingListByRegion = new ArrayList<MatchingsDTO>();

		for(int i=0; i<resList.size(); i++) {
			String region = resList.get(i).getFieldAddress().split(" ")[1];
			for(int j=0; j<matchingRegionList.size(); j++) {
				if((region.equals(matchingRegionList.get(j)))) {
					matchingListByRegion.add(resList.get(i));
				}
			}
		}

		return matchingListByRegion;
	}

	public FieldsDTO getFieldInfo(int fieldSeq) {
		FieldsDTO fDTO = null;
		fDTO = fm.selectFieldInfo(fieldSeq);

		return fDTO;
	}

	public boolean addMatcings(AddMatchingDataDTO dto) {
		List<Integer> matchingSeqList = new ArrayList<Integer>();
		for(int i=0; i<dto.getMdto().size(); i++) {
			if(dto.getMdto().get(i).getMatchingSeq() == 0) {
				addMatchingsDTO aDTO = addMatchingsDTO.builder()
						.matchingDate(dto.getMdto().get(i).getMatchingDate())
						.matchingTime(dto.getMdto().get(i).getMatchingTime())
						.fieldSeq(dto.getMdto().get(i).getFieldSeq())
						.build();
				mm.insertMatchings(aDTO);
				matchingSeqList.add(mm.selectMaxMatchingSeq());
			}
			else {
				if(!mm.selectMatchingStatus(dto.getMdto().get(i).getMatchingSeq()).equals("매칭중")) {
					return false;
				}
			}
		}
		
		int matchingAddSeq = 0;
		if(dto.getType().equals("개인")) {
			mm.insertMatchingAdds(dto.getUserId());
			matchingAddSeq = mm.selectMatchingAddSeq(dto.getUserId());
		}
		else if(dto.getType().equals("팀")) {
			int teamSeq = tm.selectTeamSeq(dto.getUserId());
			mm.insertMatchingAddsByTeam(teamSeq);
			matchingAddSeq = mm.selectMatchingAddSeqByTeam(teamSeq);
		}
		System.out.println("신청 정보: " + matchingAddSeq);
		int j = 0;
		for(int i=0; i<dto.getMdto().size(); i++) {
			if(dto.getType().equals("팀")) {
				String teamName = tm.selectTeamNameById(dto.getUserId());
				MatchingAddListsDTO addListDTO = MatchingAddListsDTO.builder()
						.matchingSeq(dto.getMdto().get(i).getMatchingSeq())
						.matchingAddSeq(matchingAddSeq)
						.team(teamName)
						.build();
				mm.insertMatchingAddListsByTeam(addListDTO);
				MatchingCountDTO mcDTO = MatchingCountDTO.builder()
						.matchingSeq(dto.getMdto().get(i).getMatchingSeq())
						.teamTier(tm.selectTeamTier(dto.getUserId()))
						.build();
				int teamCount = mm.selectMatchingMemberCountByTeam(mcDTO);
				
				if(teamCount == 2) {
					mm.updateMatchings(addListDTO.getMatchingSeq());
					mm.updateMatchingAddLists(mcDTO);
				}
			}
			else if(dto.getType().equals("개인")) {
				MatchingAddListsDTO addListDTO = MatchingAddListsDTO.builder()
						.matchingSeq(dto.getMdto().get(i).getMatchingSeq())
						.matchingAddSeq(matchingAddSeq)
						.build();
				if(addListDTO.getMatchingSeq() == 0) {
					addListDTO.setMatchingSeq(matchingSeqList.get(j));
					j++;
				}
				mm.insertMatchingAddLists(addListDTO);
				MatchingCountDTO mcDTO = MatchingCountDTO.builder()
						.matchingSeq(addListDTO.getMatchingSeq())
						.userTier(dto.getUserTier())
						.build();
				int playerCount = mm.selectMatchingMemberCount(mcDTO);
				
				if(playerCount == 10) {
					mm.updateMatchings(addListDTO.getMatchingSeq());
					mm.updateMatchingAddLists(mcDTO);
//					List<Integer> matchingAddListSeq = mm.selectMatchingMember(mcDTO);
//					System.out.println(matchingAddListSeq);
//					int playerNumber = 1;
//					for(int j=0; j<matchingAddListSeq.size(); j++) {
//						if(j < 5) {
//							mm.updateTeamToA(matchingAddListSeq.get(j));
//							PlayerNumberDTO pnDTO = PlayerNumberDTO.builder()
//									.matchingAddListSeq(matchingAddListSeq.get(j))
//									.playerNumber(playerNumber)
//									.build();
//							mm.updatePlayerNumber(pnDTO);
//							playerNumber++;
//							if(playerNumber == 6) {
//								playerNumber = 1;
//							}
//						}
//						else {
//							mm.updateTeamToB(matchingAddListSeq.get(j));
//							PlayerNumberDTO pnDTO = PlayerNumberDTO.builder()
//									.matchingAddListSeq(matchingAddListSeq.get(j))
//									.playerNumber(playerNumber)
//									.build();
//							mm.updatePlayerNumber(pnDTO);
//							playerNumber++;
//						}
//					}
				}
			}
		}

		return true;
	}
	
	public List<MatchingConditionDTO> getMatchingAddResult(AddMatchingDataDTO addDTO){
		List<MatchingConditionDTO> mcDTOList = null;
		int matchingAddSeq = 0;
		
		if(addDTO.getType().equals("개인")) {
			matchingAddSeq = mm.selectMatchingAddSeq(addDTO.getUserId());
			System.out.println("신청결과: " + matchingAddSeq);
		}
		else if(addDTO.getType().equals("팀")) {
			int teamSeq = tm.selectTeamSeq(addDTO.getUserId());
			matchingAddSeq = mm.selectMatchingAddSeqByTeam(teamSeq);
		}
		
		mcDTOList = mm.selectMatchingAddResult(matchingAddSeq);
		return mcDTOList;
	}
}