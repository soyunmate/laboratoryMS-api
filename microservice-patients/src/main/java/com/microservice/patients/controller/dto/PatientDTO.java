package com.microservice.patients.controller.dto;

import com.microservice.patients.entities.Patient;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    @NotBlank
    private String rut;
    @NotBlank
    private String email;
    @NotBlank
    private int phone;

    private List<Long> sampleIds;

    public PatientDTO(Patient patient) {
        this.id = patient.getId();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.rut = patient.getRut();
        this.email = patient.getEmail();
        this.phone = patient.getPhone();
        this.sampleIds = patient.getSampleIds();
    }
}
