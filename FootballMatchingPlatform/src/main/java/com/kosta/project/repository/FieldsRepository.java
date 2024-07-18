package com.kosta.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kosta.project.domain.Field;

public interface FieldsRepository extends JpaRepository<Field, Long>{
    List<Field> findByManager(String managerId);
}