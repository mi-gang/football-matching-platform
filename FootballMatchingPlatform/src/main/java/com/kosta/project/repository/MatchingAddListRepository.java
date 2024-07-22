package com.kosta.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.project.domain.MatchingAddList;

public interface MatchingAddListRepository extends JpaRepository<MatchingAddList, Integer>{
	List<MatchingAddList> findByMatching_MatchingSeqAndTeamNot(int matchingSeq, String team);
}
