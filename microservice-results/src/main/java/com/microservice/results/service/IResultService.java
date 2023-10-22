package com.microservice.results.service;

import com.microservice.results.entities.Result;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IResultService {
    List<Result> findByPatientId(Long patientId);
    List<Result> findBySampleId(Long sampleId);


    Optional<Result> findById(Long id);

    void save(Result result);

    void deleteById(Long id);


}
