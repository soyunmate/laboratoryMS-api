package com.microservice.samples.controller.dto;

import com.microservice.samples.entities.Sample;
import com.microservice.samples.entities.enums.SampleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SampleDTO {
    @Enumerated(EnumType.STRING)
    @NotNull
    private SampleType type;
    @NotNull
    private Long patientId;
    @NotNull
    private LocalDateTime collectionDate;
    @NotNull
    private LocalDateTime receptionDate;
    private LocalDateTime acceptanceDate;
    @NotEmpty
    private Set<Long> requestedExamsId;
    private Set<Long> resultListId;
    private LocalDateTime reportingDate;
    private Long reporterId;

    public SampleDTO(Sample sample) {
        this.type = sample.getType();
        this.patientId = sample.getPatientId();
        this.collectionDate = sample.getCollectionDate();
        this.receptionDate = sample.getReceptionDate();
        this.acceptanceDate = sample.getAcceptanceDate();
        this.requestedExamsId = sample.getRequestedExamsId();
        this.resultListId = sample.getResultListId();
        this.reportingDate = sample.getReportingDate();
        this.reporterId = sample.getReporterId();
    }

}
