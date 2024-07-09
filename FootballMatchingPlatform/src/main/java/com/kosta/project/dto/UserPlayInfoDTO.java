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
	private int userId;
	private String nickname;
	private int playerNumber;
}
