package com.kosta.project.repository;

import com.kosta.project.domain.FieldSchedule;
import com.kosta.project.dto.Manager.CloseTimeDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FieldScheduleRepository extends JpaRepository<FieldSchedule, Integer> {

	@Query("SELECT new com.kosta.project.dto.Manager.CloseTimeDTO(fs.closedDate, fs.closedTime) " +
	           "FROM FieldSchedule fs WHERE fs.closedDate = :matchingDate AND fs.fields.fieldSeq = :fieldSeq")
    List<CloseTimeDTO> findClosedDatesAndClosedTimeByFieldSeq(@Param("matchingDate") LocalDate date,@Param("fieldSeq") int fieldSeq);
}