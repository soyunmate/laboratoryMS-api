package com.microservice.samples.entities;

import com.microservice.samples.entities.enums.SampleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "samples")
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SampleType type;
    private Long patientId;
    private LocalDateTime collectionDate;
    private LocalDateTime receptionDate;
    private LocalDateTime acceptanceDate;
    private Set<Long> requestedExamsId;
    private Set<Long> resultListId;
    private LocalDateTime reportingDate;
    private Long reporterId;
}
