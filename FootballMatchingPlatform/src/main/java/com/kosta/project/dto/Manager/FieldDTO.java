package com.kosta.project.dto.Manager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldDTO {
	private int fieldSeq;
	private String fieldName;
}
