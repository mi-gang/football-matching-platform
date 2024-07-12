package com.kosta.project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddMatchingDataDTO {
	String type;
	String userId;
	String userTier;
	List<MatchingsDTO> mDTO;
}