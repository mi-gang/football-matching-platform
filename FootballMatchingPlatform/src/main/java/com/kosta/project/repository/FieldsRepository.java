package com.kosta.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kosta.project.domain.Field;
import com.kosta.project.dto.FieldDTO;
import com.kosta.project.dto.FieldInfoDTO;

public interface FieldsRepository extends JpaRepository<Field, Long>{
	
	@Query(value="SELECT new com.kosta.project.dto.FieldInfoDTO(m.name, m.manager_id, m.phone_number, m.email,"
			+ "f.field_seq, f.field_name, f.field_address, "
			+ "f.shower_room, f.rent_ball, f.rent_shoes,"
			+ "f.parking, f.sell_drink, f.field_content)"
	+"FROM fields f JOIN managers m ON f.manager_id = m.manager_id WHERE f.field_seq = :fieldSeq"
	,nativeQuery = true)
	FieldInfoDTO fieldsLeftJoin(Long fieldSeq);	
	
//	@Query(value="SELECT m.name, m.manager_id as managerId, m.phone_number as phoneNumber, m.email, "
//	+"f.field_seq as fieldSeq, f.field_name as fieldName, f.field_address as fieldAddress, "
//	+"f.field_address_detail as fieldAddressDetail, f.field_status as fieldStatus, "
//	+"f.shower_room as showerRoom, f.rent_ball as rentBall, f.rent_shoes as rentShoes, "
//	+"f.parking, f.sell_drink as sellDrink, f.field_content as fieldContent "
//	+"FROM fields f JOIN managers m ON f.manager_id = m.manager_id WHERE f.field_seq = :fieldSeq"
//	,nativeQuery = true)
//	FieldInfoDTO fieldsLeftJoin(Long fieldSeq);	
	
	List<Field> findByManager_ManagerId(String managerId);
	
	
	
}