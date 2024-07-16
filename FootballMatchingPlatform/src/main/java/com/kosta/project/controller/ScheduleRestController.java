package com.kosta.project.controller;

import com.kosta.project.dto.MatchingScheduleListDTO;
import com.kosta.project.dto.UserMatchingInfoDTO;
import com.kosta.project.dto.UserPlayInfoDTO;
import com.kosta.project.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleRestController {
    private final ScheduleService scheduleService;

    @GetMapping("/month/{month}")
    public ResponseEntity<Collection<MatchingScheduleListDTO>> getMatchingListByMonth(@PathVariable int month) {
        // 세션으로 유저 아이디 구하기
        // @SessionAttribute("userId") String userId
        String userId = "user001";
        Collection<MatchingScheduleListDTO> matchingScheduleListDTOS = new ArrayList<>();
        matchingScheduleListDTOS = scheduleService.getMatchingListByMonth(userId, month);

        return new ResponseEntity<>(matchingScheduleListDTOS, HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<Collection<MatchingScheduleListDTO>> getMatchingListByDate(@PathVariable String date) {
        // 날짜 포맷 검증
        if (!isValidDateFormat(date)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 세션으로 유저 아이디 구하기
        String userId = "user001";
        Collection<MatchingScheduleListDTO> matchingScheduleListDTOS = new ArrayList<>();
        matchingScheduleListDTOS = scheduleService.getMatchingListByDate(userId, date);

        return new ResponseEntity<>(matchingScheduleListDTOS, HttpStatus.OK);
    }

    @GetMapping("/matches/count")
    public ResponseEntity<Integer> getMatchingListCount() {

        // 세션으로 유저 아이디 구하기
        // @SessionAttribute("userId") String userId
        String userId = "user001";
        int totalCount = scheduleService.getMatchingListCount(userId);

        return new ResponseEntity<>(totalCount, HttpStatus.OK);
    }

    @GetMapping("/matches")
    public ResponseEntity<Collection<MatchingScheduleListDTO>> getMatchingList() {

        // 세션으로 유저 아이디 구하기
        // @SessionAttribute("userId") String userId
        String userId = "user001";
        Collection<MatchingScheduleListDTO> matchingScheduleListDTOS = new ArrayList<>();
        matchingScheduleListDTOS = scheduleService.getMatchingList(userId);

        return new ResponseEntity<>(matchingScheduleListDTOS, HttpStatus.OK);
    }

    @PatchMapping("/{matchingSeq}/payment")
    public ResponseEntity<String> setPayStatus(
            @PathVariable int matchingSeq) {

        //  @SessionAttribute("userId") String userId
        String userId = "user001";

        try {
            scheduleService.setPayStatus((UserMatchingInfoDTO.builder().matchingSeq(matchingSeq).userId(userId).build()));
            return ResponseEntity.ok("Payment status updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update payment status.");
        }
    }

    @DeleteMapping("/matching-add-list-seq/{matchingAddListSeq}")
    public ResponseEntity<Void> removeMatching(@PathVariable int matchingAddListSeq) {
        try {
            scheduleService.revmoveMatching(matchingAddListSeq);
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
    public ResponseEntity<Collection<UserPlayInfoDTO>> getOpposingTeamPlayerList(@PathVariable int matchingSeq) {
        // 세션으로 유저 아이디 구하기
        // @SessionAttribute("userId") String userId
        String userId = "user001";

        Collection<UserPlayInfoDTO> userPlayInfoDTOS = new ArrayList<>();
        userPlayInfoDTOS = scheduleService.getOpposingTeamPlayerList(UserMatchingInfoDTO.builder().userId(userId).matchingSeq(matchingSeq).build());

        return new ResponseEntity<>(userPlayInfoDTOS, HttpStatus.OK);
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
