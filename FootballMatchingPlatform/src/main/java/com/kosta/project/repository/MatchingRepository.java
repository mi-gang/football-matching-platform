package com.kosta.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kosta.project.domain.Matching;
import com.kosta.project.dto.Manager.MatchingDTO;

public interface MatchingRepository extends JpaRepository<Matching, Integer> {
    //특정 구장 특정 달 매칭 중 매칭상태가 경기확정인 매칭의 매칭날짜 가져오기
    @Query("SELECT m.matchingDate FROM Matching m WHERE m.fields.fieldSeq = :fieldSeq "
         + "AND m.matchingStatus = '경기확정' "
         + "AND FUNCTION('MONTH', m.matchingDate) = :month "
         + "AND FUNCTION('YEAR', m.matchingDate) = :year")
    List<LocalDate> findConfirmedMatchingDatesByFieldSeqAndMonth(@Param("fieldSeq") int fieldSeq,
                                                                  @Param("month") int month,
                                                                  @Param("year") int year);

    @Query("SELECT new com.kosta.project.dto.Manager.MatchingDTO(m.matchingSeq, m.matchingDate, m.matchingTime, " +
           "m.fastAddStatus, m.matchingStatus, m.matchingTier, m.aScore, m.bScore, m.fields.fieldSeq) " +
           "FROM Matching m WHERE m.matchingDate = :matchingDate AND m.fields.fieldSeq = :fieldSeq " +
    		 "AND m.matchingStatus IN ('경기확정', '경기완료')")
    List<MatchingDTO> findMatchingsByDateAndField(@Param("matchingDate") LocalDate matchingDate, 
                                                   @Param("fieldSeq") int fieldSeq);
    @Query("SELECT new com.kosta.project.dto.Manager.MatchingDTO(m.matchingSeq, m.matchingDate, m.matchingTime, " +
            "m.fastAddStatus, m.matchingStatus, m.matchingTier, m.aScore, m.bScore, m.fields.fieldSeq) " +
            "FROM Matching m WHERE m.matchingSeq = :matchingSeq")
    MatchingDTO findMatchingDTOById(int matchingSeq);
}
