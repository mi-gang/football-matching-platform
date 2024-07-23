package com.kosta.project.repository;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.domain.Field;
import com.kosta.project.domain.Manager;
import com.kosta.project.dto.FieldDTO;
import com.kosta.project.dto.FieldInfoDTO;
import com.kosta.project.repository.FieldMapper;

import lombok.Builder;

@SpringBootTest
public class FieldRepositoryTest {
	@Autowired
	FieldRepository fr;
	
	//@Test
	void getFieldListTest() {
		//System.out.println(fr.findByManager_ManagerId("m0000"));
		//System.out.println(fr.findAll());
	}
	
	//@Test
	void getTEst() {
		
		Manager m = fr.findById(5).get().getManager();		
		System.out.println(m.getName());
	}
	

	@Test
	void getTest2() {	// 구장 등록
		Manager m = new Manager();
		m.updateId("m0000");
		Field f = Field.builder()
				.fieldName("코리아 FC 구장")
				.fieldAddress("서울 양천구")
				.fieldAddressDetail("양천대로 523번길 10층")
				.postalCode("11887")
				.fieldContent("안전한 풋살되세요")
				.businessRegistration("c:/temp/usiness_registration")
				.fieldStatus(0)
				.showerRoom(false)
				.rentBall(false)
				.rentShoes(true)
				.parking(true)
				.sellDrink(true)
				.manager(m)
				.build();
		fr.save(f);
	}
	@Test
	void getTest122() {	// 구장 등록
		Manager m = new Manager();
		m.updateId("qwe4321");
		Field f = Field.builder()
				.fieldName("니혼 FC 구장")
				.fieldAddress("서울 양천구")
				.fieldAddressDetail("양천대로 523번길 10층")
				.postalCode("11887")
				.fieldContent("안전한 풋살되세요")
				.businessRegistration("c:/temp/usiness_registration")
				.fieldStatus(0)
				.showerRoom(false)
				.rentBall(false)
				.rentShoes(true)
				.parking(true)
				.sellDrink(true)
				.manager(m)
				.build();
		fr.save(f);
	}
	@Test
	void getTest1242() {	// 구장 등록
		Manager m = new Manager();
		m.updateId("asd1234");
		Field f = Field.builder()
				.fieldName("도이치 FC 구장")
				.fieldAddress("서울 금천구")
				.fieldAddressDetail("양천대로 523번길 10층")
				.postalCode("11887")
				.fieldContent("시설이 깔끔합니다. 안전한 풋살되세요")
				.businessRegistration("c:/temp/usiness_registration")
				.fieldStatus(0)
				.showerRoom(false)
				.rentBall(false)
				.rentShoes(true)
				.parking(true)
				.sellDrink(true)
				.manager(m)
				.build();
		fr.save(f);
	}
	@Test
	void getTest122342() {	// 구장 등록
		Manager m = new Manager();
		m.updateId("asd0917");
		Field f = Field.builder()
				.fieldName("리우 FC 구장")
				.fieldAddress("서울 금천구")
				.fieldAddressDetail("금천대로 523번길 10층")
				.postalCode("11887")
				.fieldContent("저희 풋살 구장을 이용해주셔서 감사합니다. 안전한 풋살되세요")
				.businessRegistration("c:/temp/usiness_registration")
				.fieldStatus(0)
				.showerRoom(false)
				.rentBall(false)
				.rentShoes(true)
				.parking(true)
				.sellDrink(true)
				.manager(m)
				.build();
		fr.save(f);
	}
	@Test
	void getTes124t2() {	// 구장 등록
		Manager m = new Manager();
		m.updateId("qwe1234");
		Field f = Field.builder()
				.fieldName("일반 FC 구장")
				.fieldAddress("서울 금천구")
				.fieldAddressDetail("금천대로 523번길 10층")
				.postalCode("11887")
				.fieldContent("서비스가 좋아요. 안전한 풋살되세요")
				.businessRegistration("c:/temp/usiness_registration")
				.fieldStatus(0)
				.showerRoom(false)
				.rentBall(false)
				.rentShoes(true)
				.parking(true)
				.sellDrink(true)
				.manager(m)
				.build();
		fr.save(f);
	}
	@Test
	void getTes124t52() {	// 구장 등록
		Manager m = new Manager();
		m.updateId("zxc1234");
		Field f = Field.builder()
				.fieldName("코파 FC 구장")
				.fieldAddress("서울 금천구")
				.fieldAddressDetail("금천대로 523번길 10층")
				.postalCode("11887")
				.fieldContent("안전한 풋살되세요")
				.businessRegistration("c:/temp/usiness_registration")
				.fieldStatus(0)
				.showerRoom(false)
				.rentBall(false)
				.rentShoes(true)
				.parking(true)
				.sellDrink(true)
				.manager(m)
				.build();
		fr.save(f);
	}
	
	
	
	
	
	
	
	

	//@Test
	void updateTest() {
		Field f = new Field();
		//Field dto = fr.findById(f.getFieldSeq().get);
		
		f.updateField(f);
		
		fr.save(f);
	}

}
