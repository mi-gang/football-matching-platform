package com.kosta.project.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserScoreInfoDTO {
    private List<UserMatchingInfoDTO> infoDTOS;
}
