package com.microservice.samples.service;

import com.microservice.samples.entities.Sample;

import java.util.List;
import java.util.Optional;

public interface ISampleService {

    Optional<Sample> findById(Long id);

    List<Sample> findByPatientId(Long patientId);

    List<Sample> findAll();

    void save(Sample sample);

    void deleteById(Long id);
}
