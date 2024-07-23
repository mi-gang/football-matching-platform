package com.kosta.project.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FieldListForSystemManagerDTO {
	private String fieldName;
    private String fieldAddress;
    private int fieldStatus;
    private String managerName;
    private String phoneNumber;
    private int fieldSeq;
    private LocalDate fieldApprovalDate;
    
    
    public FieldListForSystemManagerDTO(String fieldName, String fieldAddress, int fieldStatus, String managerName, String phoneNumber) {
        this.fieldName = fieldName;
        this.fieldAddress = fieldAddress;
        this.fieldStatus = fieldStatus;
        this.managerName = managerName;
        this.phoneNumber = phoneNumber;
    }
}
