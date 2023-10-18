package com.microservice.samples.service.impl;

import com.microservice.samples.entities.Sample;
import com.microservice.samples.repository.SampleRepository;
import com.microservice.samples.service.ISampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SampleServiceImpl implements ISampleService {
    @Autowired
    private SampleRepository sampleRepository;
    @Override
    public Optional<Sample> findById(Long id) {
        return sampleRepository.findById(id);
    }

    @Override
    public List<Sample> findByPatientId(Long patientId) {
        return sampleRepository.findByPatientId(patientId);
    }

    @Override
    public List<Sample> findAll() {
        return (List<Sample>) sampleRepository.findAll();
    }

    @Override
    public void save(Sample sample) {
        sampleRepository.save(sample);
    }

    @Override
    public void deleteById(Long id) {
        sampleRepository.deleteById(id);
    }
}
