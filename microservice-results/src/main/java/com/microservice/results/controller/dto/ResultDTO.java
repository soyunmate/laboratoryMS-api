package com.microservice.results.controller.dto;

import com.microservice.results.entities.Result;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDTO {
    @NotNull
    private Long examId;
    @NotNull
    private Long patientId;
    @NotNull
    private Long sampleId;
    private LocalDateTime date;
    private String resultData;
    private String url;

    public ResultDTO(Result result) {
        this.examId = result.getExamId();
        this.patientId = result.getPatientId();
        this.sampleId = result.getSampleId();
        this.date = result.getDate();
        this.resultData = result.getResultData();
        this.url = result.getUrl();
    }
}
