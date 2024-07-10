package com.kosta.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPlayInfoDTO {
	private String userId;
	private String nickname;
	private char team;
	private int playerNumber;
}
