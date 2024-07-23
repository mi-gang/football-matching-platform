package com.kosta.project.dto;

import org.springframework.data.projection.ProjectionFactory;

public interface FieldListProjection extends ProjectionFactory{

    String getFieldName();
    String getFieldAddress();
    int getFieldStatus();
    String getManagerName();
    String getPhoneNumber();
    int getFieldSeq();
}
