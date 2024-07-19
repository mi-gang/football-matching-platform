package com.kosta.project.dto;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.project.domain.Field;
import com.kosta.project.domain.FieldImage;

import lombok.Data;

@Data
public class ImageUploadDTO {
	 private MultipartFile file;
	 
	 public FieldImage toEntity(String url, Field field) {
		 return FieldImage.builder()
				 .fieldImg(url)
				 .field(field)
				 .build();
	 }

}
