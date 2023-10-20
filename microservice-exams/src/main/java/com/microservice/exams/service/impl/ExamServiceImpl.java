package com.microservice.exams.service.impl;

import com.microservice.exams.entities.Exam;
import com.microservice.exams.repository.ExamRepository;
import com.microservice.exams.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ExamServiceImpl implements IExamService {
    @Autowired
    private ExamRepository examRepository;

    @Override
    public Optional<Exam> findByCode(String code) {
        return examRepository.findByCode(code);
    }

    @Override
    public List<Exam> findByLabId(Long labId) {
        return examRepository.findByLabId(labId);
    }

    @Override
    public Optional<Exam> findById(Long id) {
        return examRepository.findById(id);
    }

    @Override
    public List<Exam> findAll() {
        return (List<Exam>) examRepository.findAll();
    }

    @Override
    public void save(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public void deleteById(Long id) {
        examRepository.deleteById(id);
    }
}
