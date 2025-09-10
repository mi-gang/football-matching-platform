package com.kosta.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kosta.project.domain.Field;
import com.kosta.project.domain.FieldImage;
import com.kosta.project.dto.FieldDTO;
import com.kosta.project.dto.FieldInfoDTO;
import com.kosta.project.dto.FieldListForSystemManagerDTO;

public interface FieldRepository extends JpaRepository<Field, Integer>{
    List<Field> findByManager_ManagerId(String managerId);
    
    @Query("SELECT f FROM Field f WHERE f.manager.id = :managerId AND f.fieldApprovalDate IS NOT NULL")
    List<Field> findApprovedFieldsByManagerId(@Param("managerId") String managerId);
    
    
    
    
    
    
    
    
    // 구장 신청 리스트
    //List<Field> findByFieldStatus(Long fieldStatus);
    
    // 지역 필터 구장 신청 리스트
    List<Field> findByFieldAddressLike(String fieldAddress);
    
    // 지역 필터와 검색 구장 신청 리스트
    List<Field> findByFieldNameLikeAndFieldAddressLike(String fieldName, String fieldAddress);
}