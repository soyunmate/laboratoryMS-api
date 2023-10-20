package com.microservice.exams.repository;

import com.microservice.exams.entities.Exam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends CrudRepository<Exam, Long> {

    Optional<Exam> findByCode(String code);
    List<Exam> findByLabId(Long labId);
}
