package com.kosta.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kosta.project.domain.Field;
import com.kosta.project.domain.Manager;

public interface ManagerRepository extends JpaRepository<Manager, String>{
	Manager findByManagerIdAndPassword(String managerId, String password);
}