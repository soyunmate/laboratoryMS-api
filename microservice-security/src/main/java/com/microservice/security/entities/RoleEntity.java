package com.microservice.security.entities;

import com.microservice.security.entities.enums.ERole;
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
@Table(name = "roles")
public class RoleEntity {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private ERole name;
}
