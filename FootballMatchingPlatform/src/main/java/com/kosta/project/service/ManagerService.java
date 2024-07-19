package com.kosta.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.project.domain.Field;
import com.kosta.project.domain.FieldImage;
import com.kosta.project.domain.Manager;
import com.kosta.project.dto.ImageUploadDTO;
import com.kosta.project.dto.InquiryDTO;
import com.kosta.project.dto.TeamDTO;
import com.kosta.project.dto.UserDTO;
import com.kosta.project.repository.FieldImgRepository;
import com.kosta.project.repository.FieldRepository;
import com.kosta.project.repository.InquiryMapper;
import com.kosta.project.repository.ManagerRepository;
import com.kosta.project.repository.TeamMapper;
import com.kosta.project.repository.UserMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerService {
	private final ManagerRepository mr;
	private final FieldRepository fr;
	private final FieldImgRepository fir;

	
	public boolean getLoginResult(String managerId, String password) {
		boolean result = false;
		System.out.println(mr.findByManagerIdAndPassword(managerId, password));
		if(mr.findByManagerIdAndPassword(managerId, password) != null) {
			result = true;
		}
		
		return result;
	}
	
	public Long addField(Field dto, String managerId, List<MultipartFile> files) {
		Field f = new Field();
		Manager m = new Manager();
		m.updateId(managerId);
		dto.updateManagerId(m);
		f = fr.save(dto);
		
		if(f.getFieldSeq() != null) {
			
			for(MultipartFile file : files) {
				
				
			}
			
			
		}
		return f.getFieldSeq();
	}

}
