package com.kosta.project.dto;

import java.sql.Date;

import com.kosta.project.domain.Field;
import com.kosta.project.domain.FieldImage;
import com.kosta.project.domain.Manager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class FieldInfoDTO {

	private Field f;
	private Manager m;
	private FieldImage fi;

}
