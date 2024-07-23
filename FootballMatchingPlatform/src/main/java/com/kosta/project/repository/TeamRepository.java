package com.kosta.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.project.domain.MatchingAdd;
import com.kosta.project.domain.Team;
import com.kosta.project.domain.User;

public interface TeamRepository extends JpaRepository<Team, Integer>{

}
