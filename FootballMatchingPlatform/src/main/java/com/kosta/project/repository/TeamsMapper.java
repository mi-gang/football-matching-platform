package com.kosta.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.FieldsDTO;

@Mapper
public interface TeamsMapper {
	int selectTeamSeq(String userId);
	int selectTeamMemberCount(String userId);
	List<String> selectTeamMemberIds(String userId);
}
