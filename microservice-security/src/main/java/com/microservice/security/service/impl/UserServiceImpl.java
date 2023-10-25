package com.microservice.security.service.impl;

import com.microservice.security.entities.UserEntity;
import com.microservice.security.repository.UserRepository;
import com.microservice.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserEntity> findByStaffId(Long staffId) {
        return userRepository.findByStaffId(staffId);
    }

    @Override
    public void save(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
