package com.microservice.security.controller.dto;

import com.microservice.security.entities.RoleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDTO {

    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String email;
    private Long staffId;
}
