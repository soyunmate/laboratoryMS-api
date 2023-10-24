package com.microservice.results.service;

import com.microservice.results.entities.Result;

import java.util.List;
import java.util.Optional;

public interface IResultService {
    List<Result> findByPatientId(Long patientId);
    List<Result> findBySampleId(Long sampleId);


    Optional<Result> findById(Long id);

    Result save(Result result);

    void deleteById(Long id);


}
