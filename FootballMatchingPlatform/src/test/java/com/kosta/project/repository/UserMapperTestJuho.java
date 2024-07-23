package com.kosta.project.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.UserDTO;

@SpringBootTest
public class UserMapperTestJuho {
	
	@Autowired
	UserMapper um;
		
	// 유저 로그인
	@Test
	void selectUserLoginTest() {
		String userId = "user00200";
		String password = "password00200";
		System.out.println(um.selectUserLogin(userId));
	}
	
	
	// 유저 구글 로그인
	@Test
	void selectUserSnsLoginTest() {
		String userId = "user00200";
		String password = "password00200";
		System.out.println(um.selectUserSnsLogin(userId, password));
	}
	
	
	// 유저 마지막 로그인 날짜 업데이트
	@Test
	void setUserLastLoginByUserIdTest() {
		String userId = "user00200";
		um.setUserLastLoginDateByUserId(userId);
	}	
	
	
	
	// 아이디 중복 체크용 유저 아이디 불러오기
	@Test
	void selectUserIdByUserIdTest() {
		String userId = "user00200";
		System.out.println(um.selectUserIdByUserId(userId));
	}	
	
	
	
	// 닉네임 중복 체크용 닉네임 불러오기
	@Test
	void selectNicknameByNicknameTest() {
		String nickname = "nickname00200";
		System.out.println(um.selectUserNicknameByNickname(nickname));
	}	
	
	
	// 이메일 중복 체크용 이메일 불러오기
	@Test
	void selectEmailByEmailTest() {
		String email = "email00100@gmail.com";
		System.out.println(um.selectEmailByEmail(email));
	}
	
	
	// 회원가입하기
	//@Test
	void insertUserJoinTest() {
		int i = 9999999;
		//for (int i = 1000; i < 1010; i++) {
			UserDTO userDTO = new UserDTO();
			userDTO.setAddress("서울 강남구");
			
			String strDate = "2006-09-17";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = new Date();
			try {
				birthday = formatter.parse(strDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userDTO.setBirthday(birthday);
			
			userDTO.setEmail("email00" + i +"@gmail.com");
			userDTO.setGameCount(0);
			userDTO.setGender("M");
			
			Date joinDate = new Date();
			userDTO.setJoinDate(joinDate);
			
			userDTO.setLastLoginDate(joinDate);
			userDTO.setName("김철수");
			userDTO.setNickname("nickname00"  + i);
			userDTO.setPassword("password00"  + i);
			userDTO.setPhoneNumber("010-1234-1234");
			userDTO.setReportCount(0);
			userDTO.setSuspendedTime(null);
			userDTO.setUserId("user00" + i);
			userDTO.setUserRank(0);
			userDTO.setUserScore(100);
			userDTO.setUserStatus("U");
			userDTO.setUserTier("D");
			userDTO.setWinCount(0);
			
			
			
//			um.insertUserJoin(userDTO);
		//}
		
	}	
		
		
	// 이름, 이메일 일치하는 아이디 불러오기
	@Test
	void selectIdByNameAndEmailTest() {
		String name = "김철수";
		String email = "email00100@gmail.com";
		System.out.println(um.selectIdByNameAndEmail(name, email));
	}
	
	
	
	// 아이디, 이메일 일치하는 비밀번호 불러오기
	@Test
	void selelctPasswordByUserIdAndEmailTest() {
		String userId = "user00100";
		String email = "email00100@gmail.com";
		System.out.println(um.selectPasswordByUserIdAndEmail(userId, email));
	}
	
	
	// 비밀번호 업데이트하기
	@Test
	void updatePasswordByUserIdTest() {
		String userId = "user00100";
		String password = "password00100";
		um.updatePasswordByUserId(password, userId);
	}	
	
	
	// 개인 순위 top3 불러오기
//	@Test
//	void selectTopThreeUsersListTest() {
//		List<UserDTO> top3 = um.selectTopThreeUsersList();
//        for (UserDTO userDTO : top3) {
//            System.out.println("탑3 " + "순위 : " + userDTO.getUserRank() + ", 닉네임 : " + userDTO.getNickname() + ", 점수 : " + userDTO.getUserScore());
//        }
//		
//	}	
		
	
	// 개인 순위 top100 불러오기
	@Test
	void selectTopHundredUsersListTest() {
		List<UserDTO> top100 = um.selectTopHundredUsersList();
		for (UserDTO userDTO : top100) {
            System.out.println("탑100 " + "순위 : " + userDTO.getUserRank() + ", 닉네임 : " + userDTO.getNickname() + ", 점수 : " + userDTO.getUserScore());
        }
	} 
	
	
	// 내 정보 수정하기
	@Test
	void updateMyInfoByUserIdTest() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId("user00100");
		userDTO.setEmail("email00100@gmail.com");
		userDTO.setName("노묵훈");
		userDTO.setNickname("zl존묵훈");
		userDTO.setPhoneNumber("010-2009-0523");
		um.updateMyInfoByUserId(userDTO);
	}
	
	
	// 회원 상태 수정하기
	@Test
	void updateUserStatusByUserIdTest() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserStatus("D");
		userDTO.setUserId("user00101");
		userDTO.setPassword("password00101");
		um.updateUserStatusByUserId(userDTO);
	}
	
	
	// 내 정보 불러오기 
//	@Test
//	void selectMyInfoByUserIdTest() {
//		UserDTO userDTO = um.selectMyInfoByUserId("user00118", "password00118");
//		System.out.println(userDTO);
//		System.out.println(userDTO.getNickname() + 
//							userDTO.getEmail() + 
//							userDTO.getUserId() + 
//							userDTO.getName() + 
//							userDTO.getGender() +
//							userDTO.getBirthday() +
//							userDTO.getPhoneNumber());
//	}
	
	
	// 내 등급 불러오기
	@Test
	void selectMyTierAndScoreByUserIdTest() {
		System.out.println(um.selectMyTierAndScoreByUserId("user00200"));
	}
	
	
	// 내 전적 불러오기
	@Test
	void selectMyMatchedInfoByUserIdTest() {
		System.out.println(um.selectMyMatchedInfoByUserId("user00200"));
	}
	
	
	// 내 이메일, 닉네임 불러오기
	@Test
	void selectNicknameAndEmailByUserIdForMyPageTest() {
		System.out.println(um.selectNicknameAndEmailByUserIdForMyPage("user00200"));
	}
	
	
}
