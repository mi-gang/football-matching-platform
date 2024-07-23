package com.kosta.project.repository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.project.domain.Field;

@SpringBootTest
public class FieldApproveTest {

	@Autowired
	FieldRepository fr;


	// 구장 승인 (승인 날짜 업데이트)
	//@Test
	public void updateApproveTest() {
		Field field = fr.findById(8).get();
		Date date = new Date();
		Instant instant = date.toInstant();
		LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

		// 	field.setFieldApprovalDate(localDate);

		// 	fr.save(field);

		// }


		// 구장 승인 거절(field_status를 2로 변경)
//	@Test
//	public void updateFieldStatus2Test() {
//		Field field = fr.findById(8).get();

		// 	int status = 2;

		// 	field.setFieldStatus(status);

		// 	fr.save(field);
		// }


	}
}