package com.kosta.project.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "field_imgs")
public class FieldImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldImgSeq;

    @Column(name = "field_img", nullable = false)
    private String fieldImg;

    @ManyToOne
    @JoinColumn(name = "field_seq", nullable = false)
    private Field field;
    
    
    
    public void setField(Field field) {
    	this.field = field;
    	if(!field.getFieldImages().contains(this)) {
    		field.getFieldImages().add(this);
    	}
    }
}
