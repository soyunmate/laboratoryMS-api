package com.microservice.results.service.impl;

import com.microservice.results.entities.Result;
import com.microservice.results.repository.ResultRepository;
import com.microservice.results.service.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ResultServiceImpl implements IResultService {
    @Autowired
    ResultRepository resultRepository;

    @Override
    public List<Result> findByPatientId(Long patientId) {
        return resultRepository.findByPatientId(patientId);
    }

    @Override
    public List<Result> findBySampleId(Long sampleId) {
        return resultRepository.findBySampleId(sampleId);
    }


    @Override
    public Optional<Result> findById(Long id) {
        return resultRepository.findById(id);
    }

    @Override
    public void save(Result result) {
        resultRepository.save(result);
    }

    @Override
    public void deleteById(Long id) {
        resultRepository.deleteById(id);
    }
}
