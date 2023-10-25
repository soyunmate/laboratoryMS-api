package com.microservice.security.service.impl;

import com.microservice.security.entities.RoleEntity;
import com.microservice.security.repository.RoleRepository;
import com.microservice.security.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleEntity> findAll() {
        return (List<RoleEntity>) roleRepository.findAll();
    }

    @Override
    public Optional<RoleEntity> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public void save(RoleEntity role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
