package com.microservice.security.service;

import com.microservice.security.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByStaffId(Long id);

    void save(UserEntity user);

    void deleteById(Long id);
}
