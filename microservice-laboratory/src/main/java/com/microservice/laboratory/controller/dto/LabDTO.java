package com.microservice.laboratory.controller.dto;

import com.microservice.laboratory.entities.Laboratory;
import com.microservice.laboratory.entities.enums.Area;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String area;
    private Set<Long> staffIds;
    @NotEmpty
    private Set<Long> examsIds;
    private Long leadTechId;

    public LabDTO(Laboratory laboratory) {
        this.id = laboratory.getId();
        this.leadTechId = laboratory.getLeadTechId();
        this.name = laboratory.getName();
        this.area = laboratory.getArea().toString();
        this.staffIds = laboratory.getStaffIds();
        this.examsIds = laboratory.getExamsIds();
    }
}
