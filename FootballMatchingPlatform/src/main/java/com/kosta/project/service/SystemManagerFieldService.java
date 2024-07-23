package com.kosta.project.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.project.domain.Field;
import com.kosta.project.domain.Manager;
import com.kosta.project.dto.FieldListForSystemManagerDTO;
import com.kosta.project.repository.FieldRepository;
import com.kosta.project.repository.ManagerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SystemManagerFieldService {
	
	private final FieldRepository fr;
	private final ManagerRepository mr;
	
	// 구장 승인 (승인 날짜 업데이트)
	public void updateApprove(int fieldSeq) {
		Field field = fr.findById(fieldSeq).get();
		Date date = new Date();
		Instant instant = date.toInstant();
		LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		
	// 	field.setFieldApprovalDate(localDate);
		
	// 	fr.save(field);
	// }
	
	
	// 구장 승인 거절(field_status를 2로 변경
	public void updateFieldStatus2(int fieldSeq) {
		Field field = fr.findById(fieldSeq).get();
		
	// 	int status = 2;
		
	// 	field.setFieldStatus(status);
		
	// 	fr.save(field);
	// }
	
	
	// 구장 신청 리스트 불러오기
	public List<FieldListForSystemManagerDTO> getFieldList() {
		
		//List<Field> field = fr.findAll(where(Field::getFieldStatus).is(0));
		//List<FieldListForSystemManagerDTO> field = fr.findAllByFieldStatus(0);
		List<Field> field = fr.findAll();
		
		//List<Manager> manager = null;
		

		List<FieldListForSystemManagerDTO> listFLSM = new ArrayList<>();
		for (Field field2 : field) {
			Manager man = field2.getManager();
			
			Manager managerOp = mr.findById(man.getManagerId()).get();
			
			FieldListForSystemManagerDTO getDTO = new FieldListForSystemManagerDTO();
			
			getDTO.setFieldAddress(field2.getFieldAddress());
			getDTO.setFieldName(field2.getFieldName());
			getDTO.setFieldSeq(field2.getFieldSeq());
			getDTO.setFieldStatus(field2.getFieldStatus());
			getDTO.setManagerName(managerOp.getName());
			getDTO.setPhoneNumber(managerOp.getPhoneNumber());
			
			listFLSM.add(getDTO);
		}
		
		
		return listFLSM;
	}
	
	
	
	// 지역 필터 구장 신청 리스트 불러오기
	public List<FieldListForSystemManagerDTO> getFieldListByAddress(String fieldAddress) {
		
		List<Field> field = fr.findByFieldAddressLike(fieldAddress);
		//List<Manager> manager = null;
		

		List<FieldListForSystemManagerDTO> listFLSM = new ArrayList<>();
		for (Field field2 : field) {
			Manager man = field2.getManager();
			
			Manager managerOp = mr.findById(man.getManagerId()).get();
			
			FieldListForSystemManagerDTO getDTO = new FieldListForSystemManagerDTO();
			
			getDTO.setFieldAddress(field2.getFieldAddress());
			getDTO.setFieldName(field2.getFieldName());
			getDTO.setFieldSeq(field2.getFieldSeq());
			getDTO.setFieldStatus(field2.getFieldStatus());
			getDTO.setManagerName(managerOp.getName());
			getDTO.setPhoneNumber(managerOp.getPhoneNumber());
			
			listFLSM.add(getDTO);
		}
		
		
		return listFLSM;
	}
	
	
	// 지역 필터와 검색 구장 신청 리스트 불러오기
	public List<FieldListForSystemManagerDTO> getFieldListByAddressAndFieldName(String fieldAddress, String fieldName) {
		
		List<Field> field = fr.findByFieldNameLikeAndFieldAddressLike(fieldName, fieldAddress);
		
		//List<Manager> manager = null;
		

		List<FieldListForSystemManagerDTO> listFLSM = new ArrayList<>();
		for (Field field2 : field) {
			Manager man = field2.getManager();
			
			Manager managerOp = mr.findById(man.getManagerId()).get();
			
			FieldListForSystemManagerDTO getDTO = new FieldListForSystemManagerDTO();
			
			getDTO.setFieldAddress(field2.getFieldAddress());
			getDTO.setFieldName(field2.getFieldName());
			getDTO.setFieldSeq(field2.getFieldSeq());
			getDTO.setFieldStatus(field2.getFieldStatus());
			getDTO.setManagerName(managerOp.getName());
			getDTO.setPhoneNumber(managerOp.getPhoneNumber());
			
			listFLSM.add(getDTO);
		}
		
		
		return listFLSM;
	}
	
	// 구장 신청 리스트 불러오기
	public List<FieldListForSystemManagerDTO> getFieldList() {
		
		//List<Field> field = fr.findAll(where(Field::getFieldStatus).is(0));
		//List<FieldListForSystemManagerDTO> field = fr.findAllByFieldStatus(0);
		List<Field> field = fr.findAll();
		
		//List<Manager> manager = null;
		

		List<FieldListForSystemManagerDTO> listFLSM = new ArrayList<>();
		for (Field field2 : field) {
			Manager man = field2.getManager();
			
			Manager managerOp = mr.findById(man.getManagerId()).get();
			
			FieldListForSystemManagerDTO getDTO = new FieldListForSystemManagerDTO();
			
			getDTO.setFieldAddress(field2.getFieldAddress());
			getDTO.setFieldName(field2.getFieldName());
			getDTO.setFieldSeq(field2.getFieldSeq());
			getDTO.setFieldStatus(field2.getFieldStatus());
			getDTO.setManagerName(managerOp.getName());
			getDTO.setPhoneNumber(managerOp.getPhoneNumber());
			
			listFLSM.add(getDTO);
		}
		
		
		return listFLSM;
	}
	
	
	
	// 지역 필터 구장 신청 리스트 불러오기
	public List<FieldListForSystemManagerDTO> getFieldListByAddress(String fieldAddress) {
		
		List<Field> field = fr.findByFieldAddressLike(fieldAddress);
		//List<Manager> manager = null;
		

		List<FieldListForSystemManagerDTO> listFLSM = new ArrayList<>();
		for (Field field2 : field) {
			Manager man = field2.getManager();
			
			Manager managerOp = mr.findById(man.getManagerId()).get();
			
			FieldListForSystemManagerDTO getDTO = new FieldListForSystemManagerDTO();
			
			getDTO.setFieldAddress(field2.getFieldAddress());
			getDTO.setFieldName(field2.getFieldName());
			getDTO.setFieldSeq(field2.getFieldSeq());
			getDTO.setFieldStatus(field2.getFieldStatus());
			getDTO.setManagerName(managerOp.getName());
			getDTO.setPhoneNumber(managerOp.getPhoneNumber());
			
			listFLSM.add(getDTO);
		}
		
		
		return listFLSM;
	}
	
	
	// 지역 필터와 검색 구장 신청 리스트 불러오기
	public List<FieldListForSystemManagerDTO> getFieldListByAddressAndFieldName(String fieldAddress, String fieldName) {
		
		List<Field> field = fr.findByFieldNameLikeAndFieldAddressLike(fieldName, fieldAddress);
		
		//List<Manager> manager = null;
		

		List<FieldListForSystemManagerDTO> listFLSM = new ArrayList<>();
		for (Field field2 : field) {
			Manager man = field2.getManager();
			
			Manager managerOp = mr.findById(man.getManagerId()).get();
			
			FieldListForSystemManagerDTO getDTO = new FieldListForSystemManagerDTO();
			
			getDTO.setFieldAddress(field2.getFieldAddress());
			getDTO.setFieldName(field2.getFieldName());
			getDTO.setFieldSeq(field2.getFieldSeq());
			getDTO.setFieldStatus(field2.getFieldStatus());
			getDTO.setManagerName(managerOp.getName());
			getDTO.setPhoneNumber(managerOp.getPhoneNumber());
			
			listFLSM.add(getDTO);
		}
		
		
		return listFLSM;
	}
	
}
