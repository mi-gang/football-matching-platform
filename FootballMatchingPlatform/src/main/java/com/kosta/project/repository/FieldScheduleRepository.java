package com.kosta.project.repository;

import com.kosta.project.domain.FieldSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FieldScheduleRepository extends JpaRepository<FieldSchedule, Integer> {

    @Query("SELECT fs.closedDate FROM FieldSchedule fs WHERE fs.fields.fieldSeq = :fieldSeq")
    List<LocalDate> findClosedDatesByFieldSeq(@Param("fieldSeq") int fieldSeq);
}