package com.kosta.project.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.project.domain.Field;
import com.kosta.project.domain.Matching;
import com.kosta.project.domain.Field;
import com.kosta.project.dto.AddMatchingDataDTO;
import com.kosta.project.dto.Manager.CloseTimeDTO;
import com.kosta.project.dto.FieldBoxDTO;
import com.kosta.project.dto.FieldDTO;
import com.kosta.project.dto.FieldsDTO;
import com.kosta.project.dto.ImageUploadDTO;
import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingsDTO;
import com.kosta.project.dto.ScoreDTO;
import com.kosta.project.dto.UserDTO;
import com.kosta.project.dto.Manager.ManagerDTO;
import com.kosta.project.dto.Manager.MatchingDTO;
import com.kosta.project.service.ManagerService;
import com.kosta.project.service.MatchingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;



@RestController
@RequiredArgsConstructor
public class ManagerRestController {
	private final ManagerService ms;
	
	@PostMapping("/login")
	Map<String, Boolean> login(@RequestBody ManagerDTO mDTO, HttpServletRequest request) {
		boolean result = ms.getLoginResult(mDTO.getManagerId(), mDTO.getPassword());

		if(result == true) {
			HttpSession session = request.getSession();
			session.setAttribute("managerId", mDTO.getManagerId());
			return Map.of("result", result);
		}
		
		return Map.of("result", result);
	}
	
	@PutMapping("/fieldStatus/{fieldSeq}")
	Map<String, String> updateFieldStatus(@PathVariable int fieldSeq){
		ms.updateFieldStatus(fieldSeq);
		return Map.of("result", "성공");
	}
	
	//특정 구장 특정 달 경기확정인 매칭의 매칭날짜 가져오기
	@GetMapping("/confirmed/dates/{fieldSeq}")
    public List<LocalDate> getConfirmedMatchingDates(@PathVariable int fieldSeq,
                                                     @RequestParam int month,
                                                     @RequestParam int year) {
		System.out.println(ms.getConfirmedMatchingDates(fieldSeq, month, year));
        return ms.getConfirmedMatchingDates(fieldSeq, month, year);
    }


	//특정 구장 특정 날짜 매칭기록 가져오기
	@GetMapping("/date/{date}/field/{fieldSeq}")
    public ResponseEntity<List<MatchingDTO>> getMatchingsByDateAndField(
            @PathVariable("date") String date,
            @PathVariable("fieldSeq") int fieldSeq) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate matchingDate = LocalDate.parse(date, formatter); // 날짜 형식 변환
        List<MatchingDTO> matchings = ms.getMatchingsForDateAndField(matchingDate, fieldSeq);

        if (matchings.isEmpty()) {
            return ResponseEntity.noContent().build(); // 매칭 기록이 없는 경우 204 No Content 반환
        }
        
        return ResponseEntity.ok(matchings); // 매칭 기록이 있는 경우 200 OK와 함께 반환
    }
	
	//매칭 상세 정보 가져오기
	@GetMapping("/matchingInfo/{matchingSeq}")
	public ResponseEntity<MatchingDTO> getMatchingInfoByMatchingSeq(@PathVariable int matchingSeq){
		MatchingDTO matchingInfo = ms.getMatchingInfoByMatchingSeq(matchingSeq);
		
		return ResponseEntity.ok(matchingInfo);
	}
	
	//구장 닫힌 날짜 가져오기
	@GetMapping("/closedDate/{date}/fieldSeq/{fieldSeq}")
	public ResponseEntity<List<CloseTimeDTO>> getClosedDate(@PathVariable String date, @PathVariable int fieldSeq){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate formatterDate = LocalDate.parse(date, formatter); // 날짜 형식 변환
		return ResponseEntity.ok(ms.getClosedDate(formatterDate, fieldSeq));
	}
	
	// 이미지 등록
	@PostMapping("/addField/image")
	public Map<String, Boolean> addFieldByImage(@RequestPart("img") MultipartFile img, 
	@RequestPart("field") Field field, 
	@RequestPart("business") MultipartFile business,
	@SessionAttribute(name = "managerId", required = false) String managerId){

		boolean res = ms.addFieldImg(img, business, field, managerId);

		return Map.of("result", res);
	}

	// 승패 입력
	@PutMapping("/setscore/{matchingSeq}")
	public Map<String, Boolean> setScore(@PathVariable("matchingSeq") int matchingSeq, @RequestBody ScoreDTO score) {
		boolean res =  ms.updateScores(matchingSeq, score.getScoreA(), score.getScoreB());
		
		return Map.of("result", res);
	}
	
	// 취소 일정 등록
	@PostMapping("/closetime/{fieldSeq}")
	public Map<String, Boolean> addCloseTime(@PathVariable("fieldSeq") int fieldSeq, @RequestBody Map<String, Object> requestBody){
		
		List<String> selectedTimes = (List<String>) requestBody.get("selectTime");
        String selectedDay = (String) requestBody.get("selectDay");
        
        boolean res = ms.addCloseTime(fieldSeq, selectedTimes, selectedDay);
        
		return Map.of("result", true);
	}
}