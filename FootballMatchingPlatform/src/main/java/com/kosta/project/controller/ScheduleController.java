package com.kosta.project.controller;

import com.kosta.project.dto.MatchingScheduleListDTO;
import com.kosta.project.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedule/{month}")
    public ResponseEntity<Collection<MatchingScheduleListDTO>> schedule(@PathVariable int month) {
        // 세션으로 유저 아이디 구하기
        String userId = "user001";
        Collection<MatchingScheduleListDTO> matchingScheduleListDTOS = new ArrayList<>();
        matchingScheduleListDTOS = scheduleService.getMatchingListByMonth(userId, month);

        return new ResponseEntity<>(matchingScheduleListDTOS, HttpStatus.OK);
    }

}
