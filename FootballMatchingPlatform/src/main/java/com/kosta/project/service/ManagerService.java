package com.kosta.project.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.project.domain.Field;
import com.kosta.project.domain.FieldImage;
import com.kosta.project.domain.Manager;
import com.kosta.project.domain.Matching;
import com.kosta.project.dto.FieldInfoDTO;
import com.kosta.project.dto.ImageUploadDTO;
import com.kosta.project.dto.InquiryDTO;
import com.kosta.project.dto.TeamDTO;
import com.kosta.project.dto.UserDTO;
import com.kosta.project.dto.Manager.MatchingDTO;
import com.kosta.project.repository.FieldImgRepository;
import com.kosta.project.repository.FieldRepository;
import com.kosta.project.repository.InquiryMapper;
import com.kosta.project.repository.ManagerRepository;
import com.kosta.project.repository.MatchingRepository;
import com.kosta.project.repository.TeamMapper;
import com.kosta.project.repository.UserMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerService {
	private final ManagerRepository mr;
	private final FieldRepository fr;
	private final FieldImgRepository fir;
	private final MatchingRepository mtr;

	// 매니저로그인
	public boolean getLoginResult(String managerId, String password) {
		boolean result = false;

		if (mr.findByManagerIdAndPassword(managerId, password) != null) {
			result = true;
		}

		return result;
	}

	// 나의 구장 전체 리스트
	public List<Field> getFieldList(String managerId) {
		List<Field> fieldList = null;
		fieldList = fr.findByManager_ManagerId(managerId);
		return fieldList;
	}

	// 구장 상태 변경
	public void updateFieldStatus(int fieldSeq) {
		Field field = fr.findById(fieldSeq).get();
		field.setFieldStatus(1);
		fr.save(field);
	}

	// 구장 상세 정보
	public FieldInfoDTO getField(int fieldSeq) {
		FieldInfoDTO dto = new FieldInfoDTO();

		dto.setF(fr.findById(fieldSeq).get());
		dto.setM(fr.findById(fieldSeq).get().getManager());

		return dto;
	}

	//특정 구장 특정 달 매칭 중 경기확정인 매칭의 매칭날짜 가져오기
	public List<LocalDate> getConfirmedMatchingDates(int fieldSeq, int month, int year) {
        return mtr.findConfirmedMatchingDatesByFieldSeqAndMonth(fieldSeq, month, year);
    }

	//특정 구장 특정 날짜 매칭 기록 가져오기
	public List<MatchingDTO> getMatchingsForDateAndField(LocalDate matchingDate, int fieldSeq) {
        return mtr.findMatchingsByDateAndField(matchingDate, fieldSeq);
    }

	// public String setUserLastLoginDateByUserId(String userId) {
	// um.setUserLastLoginDateByUserId(userId);
	// return "ok";
	// }
	//
	// //소셜 로그인하기
	// public UserDTO getUserSnsLogin(String userId, String password) {
	// um.setUserLastLoginDateByUserId(userId);
	// return um.selectUserSnsLogin(userId,password);
	// }
	//
	// // 아이디 중복 여부 확인하기
	// public boolean getUserIdByUserId(String userId) {
	// if (um.selectUserIdByUserId(userId) == null)
	// return true;
	// else
	// return false;
	// }
	//
	// // 닉네임 중복 여부 확인하기
	// public boolean getUserNicknameByNickname(String nickname) {
	// System.out.println(nickname);
	// if (um.selectUserNicknameByNickname(nickname) > 0)
	// return false;
	// else return true;
	// }
	//
	// // 이메일 중복 여부 확인하기
	// public boolean getEmailByEmail(String email) {
	// if (um.selectEmailByEmail(email)==null)
	// return true;
	// else return false;
	// }
	//
	// //회원가입하기
	// public void addUserJoin(UserDTO dto) {
	// um.insertUserJoin(dto.getUserId(),dto.getPassword(),dto.getNickname(),dto.getName(),dto.getBirthday(),dto.getGender(),dto.getPhoneNumber(),dto.getEmail(),dto.getAddress());
	// }
	//
	// //이름, 이메일이 일치하는 아이디 불러오기
	// public String getIdByNameAndEmail(String name, String email) {
	// return um.selectIdByNameAndEmail(name, email);
	// }
	//
	// // 내 팀 정보 불러오기(마이페이지)
	// public TeamDTO getTeamInfoByUserId(String userId) {
	// return tm.selectTeamInfoByUserId(userId);
	// }
	//
	// // 내 등급 불러오기
	// public UserDTO getMyTierAndScoreByUserId(String userId) {
	// return um.selectMyTierAndScoreByUserId(userId);
	// }
	//
	// // 내 전적 불러오기
	// public UserDTO getMyMatchedInfoByUserId(String userId) {
	// return um.selectMyMatchedInfoByUserId(userId);
	// }
	//
	// // 내 이메일, 닉네임 불러오기
	// public UserDTO getNicknameAndEmailByUserIdForMyPage(String userId) {
	// return um.selectNicknameAndEmailByUserIdForMyPage(userId);
	// }
	//
	// // 내 문의내역 2개(최신 2개) 불러오기
	// public List<InquiryDTO> getTwoInquiry(String userId) {
	// return im.selectTwoInquiry(userId);
	// }
	//
	//
	//
	//
	// // 내 정보 불러오기
	// public UserDTO getMyInfoByUserId(String userId, String password) {
	// return um.selectMyInfoByUserId(userId, password);
	// }
	//
	// // 아이디, 이메일 일치하는 비밀번호 불러오기
	// public String getPasswordByUserIdAndEmail(String userId, String password) {
	// return um.selectPasswordByUserIdAndEmail(userId, password);
	// }
	//
	// // 비밀번호 업데이트하기
	// public void setPasswordByUserId(String password, String userId) {
	// um.updatePasswordByUserId(password, userId);
	// }
	//
	// // 내 정보 수정하기
	// public void setMyInfoByUserId(UserDTO userDTO) {
	// um.updateMyInfoByUserId(userDTO);
	// }
	//
	// // 회원 탈퇴하기
	// public void setUserStatusByUserId(UserDTO userDTO) {
	// um.updateUserStatusByUserId(userDTO);
	// }
	//
	// // 내 문의 내역 불러오기(전체)
	// public List<InquiryDTO> getAllInquiry(String userId) {
	// return im.selectAllInquiry(userId);
	// }
	//
	// // 선택한 문의 내역 상세 보기
	// public InquiryDTO getDetailInfoByInquirySeq (int inquirySeq) {
	// return im.selectDetailInfoByInquirySeq(inquirySeq);
	// }
	//
	// // 문의 내역 추가하기
	// public void addInquiry(InquiryDTO inquiryDTO) {
	// im.insertInquiry(inquiryDTO);
	// }

	// public Long addField(Field dto, String managerId, List<MultipartFile> files)
	// {
	// Field f = new Field();
	// Manager m = new Manager();
	// m.updateId(managerId);
	// dto.updateManagerId(m);
	// f = fr.save(dto);
	//
	// if(f.getFieldSeq() != null) {
	//
	// for(MultipartFile file : files) {
	//
	//
	// }
	//
	//
	// }
	// return f.getFieldSeq();
	// }

}
