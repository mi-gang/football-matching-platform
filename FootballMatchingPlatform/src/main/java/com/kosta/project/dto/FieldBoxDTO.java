package com.kosta.project.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldBoxDTO {
	private String fieldName;
	private String fieldAddress;
	private String fieldAddressDetail;
	private String postalCode;
	private String fieldContent;
	//private String businessRegistration;
	private boolean showerRoom;
	private boolean rentBall;
	private boolean rentShoes;
	private boolean parking;
	private boolean sellDrink;
	//private String managerId;
	
}