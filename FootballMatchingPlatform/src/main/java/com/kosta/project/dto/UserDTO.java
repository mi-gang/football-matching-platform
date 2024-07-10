package com.kosta.project.dto;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private String userId;
	private String name;
	private String password;
	private String nickname;
	private Date birthday;
	private String gender;
	private String phoneNumber;
	private String email;
	private String address;
	private int gameCount;
	private int winCount;
	private int userRank;
	private Date lastLoginDate;
	private Date joinDate;
	private int userScore;
	private String userTier;
	private int reportCount;
	private Date suspendedTime;
	private String userStatus;
	
	
}
