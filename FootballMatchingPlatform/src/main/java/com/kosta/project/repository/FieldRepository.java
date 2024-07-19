package com.kosta.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kosta.project.domain.Field;
import com.kosta.project.dto.FieldDTO;
import com.kosta.project.dto.FieldInfoDTO;

public interface FieldRepository extends JpaRepository<Field, Long>{
    List<Field> findByManager_ManagerId(String managerId);
}