package com.kosta.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.FieldsDTO;
import com.kosta.project.dto.MatchingAddListsDTO;
import com.kosta.project.dto.MatchingAddResultDTO;
import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingCountDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.dto.addMatchingsDTO;

@Mapper
public interface MatchingsMapper {
	List<MatchingsDTO> selectMatchingsList(MatchingConditionDTO dto);
	List<MatchingsDTO> selectMatchingsListByRegion(MatchingConditionDTO dto);
	void insertMatchings(addMatchingsDTO dto);
	void insertMatchingAdds(String userId);
	void insertMatchingAddsByTeam(int teamSeq);
	int selectMatchingAddSeq();
	String selectMatchingStatus(int matchingSeq);
	void insertMatchingAddLists(MatchingAddListsDTO dto);
	int selectMatchingMemberCount(MatchingCountDTO dto);
	void updateMatchings(int matchingSeq);
	void updateMatchingAddLists(MatchingCountDTO dto);
	List<MatchingAddResultDTO> selectMatchingAddResult(int matchingAddSeq);
	List<MatchingsDTO> selectFastMatchingList();
	
}
