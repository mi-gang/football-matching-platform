package com.kosta.project.service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.project.domain.Field;
import com.kosta.project.domain.FieldImage;
import com.kosta.project.domain.Manager;
import com.kosta.project.dto.FieldBoxDTO;
import com.kosta.project.dto.FieldDTO;
import com.kosta.project.dto.FieldInfoDTO;
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

	// 매니저로그인
	public boolean getLoginResult(String managerId, String password) {
		boolean result = false;

		if (mr.findByManagerIdAndPassword(managerId, password) != null) {
			result = true;
		}

		return result;
	}

	// 나의 구장 전체 리스트
	public List<Field> getFieldList(String managerId) {
		List<Field> fieldList = null;
		fieldList = fr.findByManager_ManagerId(managerId);
		return fieldList;
	}

	// 구장 상태 변경
	public void updateFieldStatus(int fieldSeq) {
		Field field = fr.findById(fieldSeq).get();
		field.setFieldStatus(1);
		fr.save(field);
	}

	// 구장 상세 정보
	public FieldInfoDTO getField(int fieldSeq) {
		FieldInfoDTO dto = new FieldInfoDTO();

		dto.setF(fr.findById(fieldSeq).get());
		dto.setM(fr.findById(fieldSeq).get().getManager());

		return dto;
	}

	

	// 구장 이미지 등록
	public boolean addFieldImg(MultipartFile img, MultipartFile business, Field dto, String managerId) {

		UUID uuid = UUID.randomUUID();
		String uuids = uuid.toString().replaceAll("-", "");
		// 확장자 추출 로직
		String fileRealName = img.getOriginalFilename();
		String businessRealfileName = business.getOriginalFilename();
		// 원본 파일 명
		String fileExtention = fileRealName.substring(fileRealName.indexOf("."), fileRealName.length());
		String businessfiledExtention = businessRealfileName.substring(businessRealfileName.indexOf("."), businessRealfileName.length());
		// 원본 파일의 확장자 추출
		String fileName = uuids + fileExtention;
		String businessfileName = uuids + businessfiledExtention;

		Manager m = new Manager();
		m.updateId(managerId);
		Field f = Field.builder()
						.fieldName(dto.getFieldName())
						.fieldAddress(dto.getFieldAddress())
						.fieldAddressDetail(dto.getFieldAddressDetail())
						.postalCode(dto.getPostalCode())
						.fieldContent(dto.getFieldContent())
						.businessRegistration(businessfileName)
						.showerRoom(dto.isShowerRoom())
						.rentBall(dto.isRentBall())
						.rentShoes(dto.isRentShoes())
						.parking(dto.isParking())
						.fieldStatus(0)
						.sellDrink(dto.isSellDrink())
						.manager(m)
						.build();

		Field upField = fr.save(f);

		boolean res = false;
		//String uploadPath = "C:\\project\\FootballMatchingPlatform\\FootballMatchingPlatform\\src\\main\\resources\\static\\upload";
		String uploadPath = "C:\\Users\\gupury\\upload\\img";
		String uploadPath2 = "C:\\Users\\gupury\\upload\\business";

		// 업로드한 파일을 서버 컴퓨터 내에 지정한 경로에 실제 저장
		File saveFile = new File(uploadPath + "\\" + fileName);
		File saveFile2 = new File(uploadPath2 +  "\\" + businessfileName);

		try {
			img.transferTo(saveFile);
			business.transferTo(saveFile2);
			FieldImage fi = FieldImage.builder()
									.fieldImg(fileName)
									.field(upField)
									.build();
			fir.save(fi);
			res = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(res);
		return res;
	}
}
