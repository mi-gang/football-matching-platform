package com.kosta.project.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude={"manager", "fieldImages"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "fields")
public class Field {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int fieldSeq; // 필드 시퀀스 (자동 증가)

	    @Column(name = "field_name", nullable = false)
	    private String fieldName; // 필드 이름

	    @Column(name = "field_address", nullable = false)
	    private String fieldAddress; // 필드 주소

	    @Column(name = "field_address_detail", nullable = false)
	    private String fieldAddressDetail; // 필드 상세 주소

	    @Column(name = "postal_code", nullable = false)
	    private String postalCode; // 우편번호

	    @Column(name = "field_content", nullable = false)
	    private String fieldContent; // 필드 내용

	    @Column(name = "business_registration", nullable = false)
	    private String businessRegistration; // 사업자 등록번호

	    @Column(name = "field_status", nullable = false)
	    private int fieldStatus; // 필드 상태 (0: 승인 대기, 1: 승인됨, 2: 거절됨)

	    @Column(name = "field_approval_date")
	    private LocalDate fieldApprovalDate; // 승인 날짜

	    @Column(name = "shower_room", nullable = false, columnDefinition = "TINYINT(1)")
	    private boolean showerRoom; // 샤워룸 여부

	    @Column(name = "rent_ball", nullable = false, columnDefinition = "TINYINT(1)")
	    private boolean rentBall; // 공 대여 여부

	    @Column(name = "rent_shoes", nullable = false, columnDefinition = "TINYINT(1)")
	    private boolean rentShoes; // 신발 대여 여부

	    @Column(name = "parking", nullable = false, columnDefinition = "TINYINT(1)")
	    private boolean parking; // 주차 여부

	    @Column(name = "sell_drink", nullable = false, columnDefinition = "TINYINT(1)")
	    private boolean sellDrink; // 음료 판매 여부

	    @Column(name = "reservation_count_of_month")
	    private Integer reservationCountOfMonth; // 월 예약 건수
	    
	    @OneToMany(mappedBy = "field")
	    private List<FieldImage> fieldImages;
	    
	    @ManyToOne
	    @JoinColumn(name = "manager_id", nullable = false)
	    private Manager manager; // 관리자 (다대일 관계)
	    
	    public void updateField(Field f) {
	    	this.showerRoom = f.showerRoom;
	    	this.rentBall = f.rentBall;
	    	this.rentShoes = f.rentShoes;
	    	this.parking = f.parking;
	    	this.sellDrink = f.sellDrink;
	    	this.fieldContent = f.getFieldContent();
	    }
	    
	    public void addImageString(String fileName) {
	    	FieldImage img = FieldImage.builder()
	    			.fieldImg(fileName)
	    			.build();
	    	fieldImages.add(img);
	    	if(img.getField() != this)
	    		img.setField(this);
	    }
	    
	    public void updateManagerId(Manager m) {
	    	this.manager = m;
	    }
	    
	    
	    public void approveField(Field f) {
	    	this.fieldStatus = f.fieldStatus;
	    	this.fieldSeq = f.fieldSeq;
	    }
	    
	    
}
