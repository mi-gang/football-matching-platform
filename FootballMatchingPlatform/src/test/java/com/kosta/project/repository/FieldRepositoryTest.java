package com.kosta.project.repository;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.domain.Field;
import com.kosta.project.domain.Manager;
import com.kosta.project.dto.FieldDTO;
import com.kosta.project.repository.FieldMapper;

import lombok.Builder;

@SpringBootTest
public class FieldRepositoryTest {
	@Autowired
	FieldsRepository fr;
	
	//@Test
	void getFieldListTest() {
		//System.out.println(fr.findByManager_ManagerId("m0000"));
		//System.out.println(fr.findAll());
	}
	
	//@Test
	void getTEst() {
		System.out.println(fr.fieldsLeftJoin(5L));
	}
	

	@Test
	void getTest2() {	// 구장 등록
		Manager m = new Manager();
		m.updateId("m0000");
		Field f = Field.builder()
				.fieldName("성수 FC 피치구장")
				.fieldAddress("서울시 광진구 자양로 78")
				.fieldAddressDetail("8층")
				.postalCode("11887")
				.fieldContent("주차장은 지하에 있습니다. 알아서 오세요")
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
