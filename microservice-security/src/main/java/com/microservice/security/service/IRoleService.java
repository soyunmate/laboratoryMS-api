package com.microservice.security.service;

import com.microservice.security.entities.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface IRoleService {

    List<RoleEntity> findAll();

    Optional<RoleEntity> findById(Long id);

    void save(RoleEntity role);

    void deleteById(Long id);
}
