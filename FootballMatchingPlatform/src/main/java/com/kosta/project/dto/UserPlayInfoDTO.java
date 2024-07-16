package com.kosta.project.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserPlayInfoDTO {
	private String userId;
	private String nickname;
	private char team;
	private int playerNumber;
}
