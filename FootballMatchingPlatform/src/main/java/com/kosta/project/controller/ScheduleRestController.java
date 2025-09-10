package com.kosta.project.controller;

import com.kosta.project.dto.*;
import com.kosta.project.repository.MatchingMapper;
import com.kosta.project.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleRestController {
    private final ScheduleService scheduleService;
    private final MatchingMapper matchingMapper;

    @GetMapping("/month/{month}")
    public ResponseEntity<Collection<MatchingScheduleListDTO>> getMatchingListByMonth(@PathVariable int month,
                                                                                      @SessionAttribute("loginUser") UserDTO userDTO) {
        System.out.println("세션 확인 : " + userDTO.getUserId());

        Collection<MatchingScheduleListDTO> matchingScheduleListDTOS = new ArrayList<>();
        matchingScheduleListDTOS = scheduleService.getMatchingListByMonth(userDTO.getUserId(), month);

        return new ResponseEntity<>(matchingScheduleListDTOS, HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<Collection<MatchingScheduleListDTO>> getMatchingListByDate(@PathVariable String date,
                                                                                     @SessionAttribute("loginUser") UserDTO userDTO) {
        // 날짜 포맷 검증
        if (!isValidDateFormat(date)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Collection<MatchingScheduleListDTO> matchingScheduleListDTOS = new ArrayList<>();
        matchingScheduleListDTOS = scheduleService.getMatchingListByDate(userDTO.getUserId(), userDTO.getUserTier(), date);

        return new ResponseEntity<>(matchingScheduleListDTOS, HttpStatus.OK);
    }

    @GetMapping("/matches/count")
    public ResponseEntity<Integer> getMatchingListCount(@SessionAttribute("loginUser") UserDTO userDTO) {

        int totalCount = scheduleService.getMatchingListCount(userDTO.getUserId());

        return new ResponseEntity<>(totalCount, HttpStatus.OK);
    }

    @GetMapping("/matches")
    public ResponseEntity<Collection<MatchingScheduleListDTO>> getMatchingList(@SessionAttribute("loginUser") UserDTO userDTO) {

        Collection<MatchingScheduleListDTO> matchingScheduleListDTOS = new ArrayList<>();
        matchingScheduleListDTOS = scheduleService.getMatchingList(userDTO.getUserId(), userDTO.getUserTier());

        return new ResponseEntity<>(matchingScheduleListDTOS, HttpStatus.OK);
    }

    @PatchMapping("/{matchingAddListSeq}/payment")
    public ResponseEntity<String> setPayStatus(@PathVariable int matchingAddListSeq,
                                               @SessionAttribute("loginUser") UserDTO userDTO) {

        try {
            scheduleService.setPayStatus((UserMatchingInfoDTO.builder().matchingAddListSeq(matchingAddListSeq).userId(userDTO.getUserId()).build()));
            return ResponseEntity.ok("Payment status updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update payment status.");
        }
    }

    @DeleteMapping("/matching-add-list-seq/{matchingAddListSeq}")
    public ResponseEntity<Void> removeMatching(@PathVariable int matchingAddListSeq) {
        try {
            scheduleService.removeMatching(matchingAddListSeq);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @PatchMapping("/matching-add-list-seq/{matchingAddListSeq}")
//    public ResponseEntity<String> cancelMatching(@PathVariable int matchingAddListSeq) {
//
//        try {
//            scheduleService.cancelMatching(matchingAddListSeq);
//            return ResponseEntity.ok("Cancel status updated successfully.");
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update cancel status.");
//        }
//    }

    @PatchMapping("/matching-add-list-seq/{matchingAddListSeq}/cancel")
    public ResponseEntity<String> cancelMatching(@PathVariable int matchingAddListSeq) {
        try {
            scheduleService.cancelMatching(matchingAddListSeq);
            return ResponseEntity.ok("Cancel status updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to update cancel status: " + e.getMessage());
        }
    }

    @GetMapping("/{matchingSeq}/opposing-players")
    public ResponseEntity<Collection<UserPlayInfoDTO>> getOpposingTeamPlayerList(@PathVariable int matchingSeq,
                                                                                 @SessionAttribute("loginUser") UserDTO userDTO) {

        Collection<UserPlayInfoDTO> userPlayInfoDTOS = new ArrayList<>();
        userPlayInfoDTOS = scheduleService.getOpposingTeamPlayerList(UserMatchingInfoDTO.builder().userId(userDTO.getUserId()).matchingSeq(matchingSeq).build());

        return new ResponseEntity<>(userPlayInfoDTOS, HttpStatus.OK);
    };

    @PostMapping("/review-scores")
    public ResponseEntity<String> setReviewScores(@RequestBody UserScoreInfoDTO userMatchingInfoDTOs,
                                                  @RequestParam int matchingAddListSeq) {

        try {
            scheduleService.setReviewScore(userMatchingInfoDTOs.getInfoDTOS(), matchingAddListSeq);
            return ResponseEntity.ok("set Review Scores successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to set Review Scores: " + e.getMessage());
        }
    };

    @GetMapping("/{matchingSeq}/my-team-players")
    public ResponseEntity<Collection<UserPlayInfoDTO>> getMyTeamPlayerList(@PathVariable int matchingSeq,
                                                                           @SessionAttribute("loginUser") UserDTO userDTO) {

        Collection<UserPlayInfoDTO> userPlayInfoDTOS = new ArrayList<>();
        userPlayInfoDTOS = scheduleService.getMyTeamPlayerList(UserMatchingInfoDTO.builder().userId(userDTO.getUserId()).matchingSeq(matchingSeq).build());

        return new ResponseEntity<>(userPlayInfoDTOS, HttpStatus.OK);
    };

    @PostMapping("report")
    public ResponseEntity<String> addReport(@RequestBody ReportDTO reportDTO,
                                            @SessionAttribute("loginUser") UserDTO userDTO) {

        reportDTO.setUserId(userDTO.getUserId());
        try {
            scheduleService.addReport(reportDTO);
            return ResponseEntity.ok("add report successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add report: " + e.getMessage());
        }
    };

    @GetMapping("/{matchingSeq}/score")
    public ResponseEntity<Map<String, Integer>> getScore(@PathVariable int matchingSeq,
                                                         @SessionAttribute("loginUser") UserDTO userDTO) {

        Map<String, Integer> map = new HashMap<>();
        map = scheduleService.getReviewScoreAndTeamScore(UserMatchingInfoDTO.builder().userId(userDTO.getUserId()).matchingSeq(matchingSeq).build());

        Integer teamScore = map.get("teamScore");
        if (teamScore != null && teamScore == 0) {
            map.put("teamScore", -10);
        } else if (teamScore != null && teamScore == 1) {
            map.put("teamScore", 10);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    };


    // 날짜 포맷 검증
    private boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
