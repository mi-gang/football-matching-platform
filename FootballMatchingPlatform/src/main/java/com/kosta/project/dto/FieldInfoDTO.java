package com.kosta.project.dto;

import java.sql.Date;

import com.kosta.project.domain.Field;
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
	private int fieldSeq;
	private String fieldName;
	private String fieldAddress;
	private String fieldAddressDetail;
	private String fieldContent;
	private boolean fieldStatus;
	private boolean showerRoom;
	private boolean rentBall;
	private boolean rentShoes;
	private boolean parking;
	private boolean sellDrink;

	private String managerId;
	private String name;
	private String phoneNumber;
	private String email;
	
	public FieldInfoDTO(int fieldSeq, String fieldName, String fieldAddress, String fieldAddressDetail,
			String fieldContent, boolean fieldStatus, boolean showerRoom, boolean rentBall, boolean rentShoes,
			boolean parking, boolean sellDrink, String managerId, String name, String phoneNumber, String email) {
		super();
		this.fieldSeq = fieldSeq;
		this.fieldName = fieldName;
		this.fieldAddress = fieldAddress;
		this.fieldAddressDetail = fieldAddressDetail;
		this.fieldContent = fieldContent;
		this.fieldStatus = fieldStatus;
		this.showerRoom = showerRoom;
		this.rentBall = rentBall;
		this.rentShoes = rentShoes;
		this.parking = parking;
		this.sellDrink = sellDrink;
		this.managerId = managerId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	


}
