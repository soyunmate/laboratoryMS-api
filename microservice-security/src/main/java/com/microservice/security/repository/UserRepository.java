package com.microservice.security.repository;

import com.microservice.security.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByStaffId(Long staffId);
}
