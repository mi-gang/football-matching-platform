package com.kosta.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldsDTO {
	String fieldName;
	String fieldImg;
	String fieldAddress;
	String fieldAddressDetail;
	String matchingTier;
	String fieldContent;
	String matchingDate;
	int matchingTime;
	boolean showerRoom;
	boolean rentBall;
	boolean rentShoes;
	boolean parking;
	boolean sellDrink;
}
