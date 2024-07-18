package com.kosta.project.service;



import java.util.ArrayList;

import java.util.Iterator;

import java.util.List;



import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;



import com.kosta.project.dto.FastMatchingConditionDTO;

import com.kosta.project.dto.FastMatchingDTO;

import com.kosta.project.dto.MatchingsDTO;



@SpringBootTest

public class FastMatchingServerTest {



	@Autowired

	private FastMatchingService fms;



	// 회원 상태 확인

	//@Test

	void getUserStatusTest() {

		System.out.println(fms.getUserStatus("user00100"));

	}





	// 정지 여부 확인 (결제 시)

	@Test

	void isSuspendedUserTest() {

		System.out.println(fms.isSuspendedUser("user010"));

	}





	// 구장 상세 정보 조회

	//@Test

	void getFieldTest() {

		System.out.println(fms.getField(3));

	}





	// 등급 확인

	//@Test

	void getMatchingTierTest() {

		System.err.println(fms.getMatchingTier(3));

	}





	// 빠른 신청 리스트1

//	@Test
//
//	void getFastMatchingListTest() {
//
//		List<FastMatchingDTO> matchingDTOList = fms.getFastMatchingList();
//
//		for (FastMatchingDTO matchingsDTO : matchingDTOList) {
//
//			System.out.println(matchingsDTO.getFieldAddress() +
//
//					matchingsDTO.getFieldAddress() +
//
//					matchingsDTO.getFieldAddressDetail() +
//
//					matchingsDTO.getMatchingDate() +
//
//					matchingsDTO.getMatchingSeq() +
//
//					matchingsDTO.getMatchingTime());
//
//		}
//
//	}









	// 빠른 신청 리스트2
//
//	@Test
//
//	void getFastMatchingListBySmallTest() {
//
//		List<FastMatchingDTO> matchingDTOList = fms.getFastMatchingListBySmall();
//
//		for (FastMatchingDTO matchingsDTO : matchingDTOList) {
//
//			System.out.println(matchingsDTO.getFieldAddress() +
//
//					matchingsDTO.getFieldAddress() +
//
//					matchingsDTO.getFieldAddressDetail() +
//
//					matchingsDTO.getFieldImg() +
//
//					matchingsDTO.getMatchingTier() +
//
//					matchingsDTO.getPlayerCount() +
//
//					matchingsDTO.getMatchingDate() +
//
//					matchingsDTO.getMatchingSeq() +
//
//					matchingsDTO.getMatchingTime());
//
//		}
//
//	}



	// 빠른 신청 리스트3

	//@Test

//	void getFastMatchingListByBigTest() {
//
//		List<FastMatchingDTO> matchingDTOList = fms.getFastMatchingListBySmall();
//
//		for (FastMatchingDTO matchingsDTO : matchingDTOList) {
//
//			System.out.println(matchingsDTO.getFieldAddress() +
//
//					matchingsDTO.getFieldAddress() +
//
//					matchingsDTO.getFieldAddressDetail() +
//
//					matchingsDTO.getFieldImg() +
//
//					matchingsDTO.getMatchingTier() +
//
//					matchingsDTO.getPlayerCount() +
//
//					matchingsDTO.getMatchingDate() +
//
//					matchingsDTO.getMatchingSeq() +
//
//					matchingsDTO.getMatchingTime());
//
//		}
//
//	}	



	// 빠른 신청 리스트4

	//@Test

	void getFastMatchingListByBigAndDateAndRegionAndTierTest() {

		FastMatchingConditionDTO fmcDTO = new FastMatchingConditionDTO();

		

		String[] addressList = {"인천 미추홀구", "인천 미추홀구", "인천 미추홀구"};

		String[] matchingTier = {"A", "B", "C", "D"};

		

		for (int i = 0; i < addressList.length; i++) {

			System.out.println("몇 번 반복?? : " + i + " : " + addressList[i]);

			fmcDTO.setFieldAddress(addressList[i]);

			fmcDTO.setMatchingDate("2024-07-10");

			

			for (int j = 0; j < matchingTier.length; j++) {

				System.out.println("진짜 몇 번째?? : " + matchingTier[j]);

				

				fmcDTO.setMatchingTier(matchingTier[j]);

			

				List<FastMatchingDTO> matchingDTOList = fms.getFastMatchingListByBigAndDateAndRegionAndTier(fmcDTO);

				for (FastMatchingDTO matchingsDTO : matchingDTOList) {

					System.out.println(matchingsDTO.getFieldAddress() +

							matchingsDTO.getFieldAddress() +

							matchingsDTO.getFieldAddressDetail() +

							matchingsDTO.getFieldImg() +

							matchingsDTO.getMatchingTier() +

							matchingsDTO.getPlayerCount() +

							matchingsDTO.getMatchingDate() +

							matchingsDTO.getMatchingSeq() +

							matchingsDTO.getMatchingTime());

				}

			}

			

		}

		

	}

	

	

	@Test

	void getFastMatchingListByBigAndDateAndRegionAndTierTest111() {

		FastMatchingConditionDTO fmcDTO = new FastMatchingConditionDTO();

		

		fmcDTO.setFieldAddress("인천 미추홀구");

		fmcDTO.setMatchingDate("2024-07-10");

		fmcDTO.setMatchingTier("D");

		

		List<FastMatchingDTO> matchingDTOList = fms.getFastMatchingListByBigAndDateAndRegionAndTier(fmcDTO);

		for (FastMatchingDTO matchingsDTO : matchingDTOList) {

			System.out.println(matchingsDTO.getFieldAddress() +

					matchingsDTO.getFieldAddress() +

					matchingsDTO.getFieldAddressDetail() +

					matchingsDTO.getFieldImg() +

					matchingsDTO.getMatchingTier() +

					matchingsDTO.getPlayerCount() +

					matchingsDTO.getMatchingDate() +

					matchingsDTO.getMatchingSeq() +

					matchingsDTO.getMatchingTime());

		}		

	}









	// 빠른 신청 리스트5

	//@Test

	void getFastMatchingListBySmallAndDateAndRegionAndTierTest() {

		FastMatchingConditionDTO fmcDTO = new FastMatchingConditionDTO();

		List<FastMatchingDTO> matchingDTOList = fms.getFastMatchingListBySmallAndDateAndRegionAndTier(fmcDTO);

		for (FastMatchingDTO matchingsDTO : matchingDTOList) {

			System.out.println(matchingsDTO.getFieldAddress() +

					matchingsDTO.getFieldAddress() +

					matchingsDTO.getFieldAddressDetail() +

					matchingsDTO.getFieldImg() +

					matchingsDTO.getMatchingTier() +

					matchingsDTO.getPlayerCount() +

					matchingsDTO.getMatchingDate() +

					matchingsDTO.getMatchingSeq() +

					matchingsDTO.getMatchingTime());

		}

	}		











}