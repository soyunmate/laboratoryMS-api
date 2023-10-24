package com.microservice.staff.entities;

import com.microservice.common.enums.Area;
import com.microservice.common.enums.Charge;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String rut;
    @Enumerated(EnumType.STRING)
    private Area area;
    @Enumerated(EnumType.STRING)
    private Charge charge;

}
