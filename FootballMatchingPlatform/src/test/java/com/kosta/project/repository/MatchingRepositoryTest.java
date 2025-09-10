package com.kosta.project.repository;


import java.time.LocalDate;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MatchingRepositoryTest {
	@Autowired
	MatchingRepository mr;
	
	//@Test
	// void findMatchingsByMonthAndFieldTest() {
	// 	int year = 2024;
	// 	int month = 7;
	// 	int fieldSeq = 2;
	// 	YearMonth yearMonth = YearMonth.of(year, month);
	// 	LocalDate startDate = yearMonth.atDay(1);
    //     LocalDate endDate = yearMonth.atEndOfMonth();

    //     System.out.println(mr.findMatchingsByMonthAndField(startDate, endDate, fieldSeq));
	// }
}