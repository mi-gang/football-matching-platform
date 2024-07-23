package com.kosta.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.project.domain.MatchingAdd;
import com.kosta.project.domain.User;

public interface UserRepository extends JpaRepository<User, String>{

}
