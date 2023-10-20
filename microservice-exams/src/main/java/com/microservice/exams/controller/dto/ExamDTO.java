package com.microservice.exams.controller.dto;

import com.microservice.common.enums.Area;
import com.microservice.exams.entities.Exam;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamDTO {
    private Long id;
    private String code;
    private String name;
    private Area labArea;
    private Long labId;
    private String indications;
    private String sampleType;

    public ExamDTO(Exam exam){
        this.id = exam.getId();
        this.code = exam.getCode();
        this.name = exam.getName();
        this.labArea = exam.getLabArea();
        this.labId = exam.getLabId();
        this.indications = exam.getIndications();
        this.sampleType = exam.getSampleType();
    }
}
