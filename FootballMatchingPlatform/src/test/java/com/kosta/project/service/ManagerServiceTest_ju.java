package com.kosta.project.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.project.domain.Field;
import com.kosta.project.domain.Manager;
import com.kosta.project.dto.FieldDTO;
import com.kosta.project.repository.FieldMapper;
import com.kosta.project.repository.FieldRepository;
import com.kosta.project.repository.ManagerRepository;

@SpringBootTest
public class ManagerServiceTest_ju {
	@Autowired
	ManagerService ms;

	//@Test
	void udpateScore(){
		int mSeq = 2;
		int aS = 3;
		int bS = 5;
		ms.updateScores(mSeq, aS, bS);
	}
	
	//@Test
	void addField() {
		Manager m = new Manager();
		Field f = Field.builder()
				.fieldName("성수 FC 오렌지구장")
				.fieldAddress("서울시 영등포구")
				.fieldAddressDetail("영등포로 25길 67")
				.postalCode("12187")
				.fieldContent("주차장은 지하에 있습니다. 샤워실 없고, 축구공 대여가능합니다.")
				.businessRegistration("c:/temp/usiness_registration")
				.fieldStatus(0)
				.showerRoom(false)
				.rentBall(false)
				.rentShoes(true)
				.parking(true)
				.sellDrink(true)
				.build();
		
		//System.out.println(ms.addField(f, "m0000"));

	}
	
}
