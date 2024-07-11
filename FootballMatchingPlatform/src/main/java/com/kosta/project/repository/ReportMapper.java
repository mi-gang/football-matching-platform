package com.kosta.project.repository;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.ReportDTO;

@Mapper
public interface ReportMapper {
	
	boolean insertReport(ReportDTO reportDTO);
	boolean selectReport(ReportDTO reportDTO);
	
}
