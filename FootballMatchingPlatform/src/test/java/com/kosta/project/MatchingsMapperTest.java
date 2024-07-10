package com.kosta.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.dto.FastMatchingConditionDTO;
import com.kosta.project.dto.MatchingAddListsDTO;
import com.kosta.project.dto.MatchingConditionDTO;
import com.kosta.project.dto.MatchingCountDTO;
import com.kosta.project.dto.addMatchingsDTO;
import com.kosta.project.repository.MatchingMapper;


@SpringBootTest
public class MatchingsMapperTest {
	@Autowired
	MatchingMapper mm;
	
	//@Test
	void selectMatchingsListTest() {
		MatchingConditionDTO dto = MatchingConditionDTO.builder()
				.matchingDate("2024-07-10")
				.matchingTime(12)
				.build();
		System.out.println(mm.selectMatchingsList(dto));
	}
	
	//@Test
	void selectMatchingsListByRegionTest() {
		MatchingConditionDTO dto = MatchingConditionDTO.builder()
				.matchingDate("2024-07-10")
				.matchingTime(12)
				.fieldAddress("인천광역시 미추홀구")
				.build();
		System.out.println(mm.selectMatchingsListByRegion(dto));
	}
	
	//@Test
	void insertMatchingsTest() {
		addMatchingsDTO dto = addMatchingsDTO.builder()
				.matchingDate("24-07-15")
				.matchingTime(8)
				.fieldSeq(3)
				.build();
		mm.insertMatchings(dto);
	}
	
	//@Test
	void insertMatchingAddsTest() {
		mm.insertMatchingAdds("user001");
	}
	
	//@Test
	void insertMatchingAddsByTeam() {
		mm.insertMatchingAddsByTeam(1);
	}
	
	//@Test
	void selectMatchingAddSeqTest() {
		System.out.println(mm.selectMatchingAddSeq());
	}
	
	//@Test
	void selectMatchingStatusTest() {
		System.out.println(mm.selectMatchingStatus(3));
	}
	
	//@Test
	void insertMatchingAddListsTest() {
		MatchingAddListsDTO dto = MatchingAddListsDTO.builder()
				.matchingSeq(3)
				.matchingAddSeq(19)
				.build();
		mm.insertMatchingAddLists(dto);
	}
	
	//@Test
	void selectMatchingMemberCountTest() {
		MatchingCountDTO dto = MatchingCountDTO.builder()
				.matchingSeq(3)
				.userTier("D")
				.build();
		System.out.println(mm.selectMatchingMemberCount(dto));
	}
	
	//@Test
	void updateMatchingsTest() {
		mm.updateMatchings(3);
	}
	
	//@Test
	void updateMatchingAddListsTest() {
		MatchingCountDTO dto = MatchingCountDTO.builder()
				.matchingSeq(3)
				.userTier("D")
				.build();
		mm.updateMatchingAddLists(dto);
	}
	
	//@Test
	void selectMatchingAddResultTest() {
		System.out.println(mm.selectMatchingAddResult(19));
	}
	
	@Test
	void selectFastMatchingListTest() {
		System.out.println(mm.selectFastMatchingList());
	}
	
	//@Test
		void selectFastMatchingListBySmallTest() {
			System.out.println(mm.selectFastMatchingListBySmall());
		}
		
		//@Test
		void selectFastMatchingListByBigTest() {
			System.out.println(mm.selectFastMatchingListByBig());
		}
		
		//@Test
		void selectFastMatchingListBySmallDateAndRegionAndTierTest() {
			FastMatchingConditionDTO dto = FastMatchingConditionDTO.builder()
					.matchingDate("24-07-01")
					.fieldAddress("인천광역시 미추홀구")
					.matchingTier("D")
					.build();
			System.out.println(mm.selectFastMatchingListBySmallAndDateAndRegionAndTier(dto));
		}
		
		@Test
		void selectFastMatchingListByBigDateAndRegionAndTierTest() {
			FastMatchingConditionDTO dto = FastMatchingConditionDTO.builder()
					.matchingDate("24-07-01")
					.fieldAddress("인천광역시 미추홀구")
					.matchingTier("D")
					.build();
			System.out.println(mm.selectFastMatchingListBySmallAndDateAndRegionAndTier(dto));
		}
		
		//@Test
		void selectMatchingTierTest() {
			System.out.println(mm.selectMatchingTier(1));
		}
}