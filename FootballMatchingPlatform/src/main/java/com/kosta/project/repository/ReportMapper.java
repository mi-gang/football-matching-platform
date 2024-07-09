package com.kosta.project.repository;

import org.apache.ibatis.annotations.Mapper;

import com.kosta.project.dto.ReportDTO;

@Mapper
public interface ReportMapper {
	
	boolean selectReport(ReportDTO reportDTO);
	boolean insertReport(ReportDTO reportDTO);

}
