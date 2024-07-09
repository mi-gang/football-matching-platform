package com.kosta.project.repository;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.FieldsDTO;

@Mapper
public interface MatchingsMapper {
	
	void insertMatchingAdds(String userId);
	void insertMatchingAddsByTeam(int teamSeq);
	int selectMatchingAddSeq();
}
