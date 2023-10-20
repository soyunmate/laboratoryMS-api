package com.microservice.exams.service;

import com.microservice.exams.entities.Exam;

import java.util.List;
import java.util.Optional;

public interface IExamService {

    Optional<Exam> findByCode(String code);
    List<Exam> findByLabId(Long labId);

    Optional<Exam> findById(Long id);

    List<Exam> findAll();

    void save(Exam exam);

    void deleteById(Long id);
}
