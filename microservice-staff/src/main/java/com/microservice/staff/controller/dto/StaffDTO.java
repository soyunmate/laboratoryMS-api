package com.microservice.staff.controller.dto;

import com.microservice.staff.entities.Staff;
import com.microservice.staff.entities.enums.Area;
import com.microservice.staff.entities.enums.Charge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffDTO {
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String rut;
    private String area;
    private String charge;


    public StaffDTO(Staff staff) {
        this.id = staff.getId();
        this.userId = staff.getUserId();
        this.firstName = staff.getFirstName();
        this.lastName = staff.getLastName();
        this.rut = staff.getRut();
        this.area = staff.getArea().toString();
        this.charge = staff.getCharge().toString();
    }
}
