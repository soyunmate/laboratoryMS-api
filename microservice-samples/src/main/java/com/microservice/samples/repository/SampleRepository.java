package com.microservice.samples.repository;

import com.microservice.samples.entities.Sample;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleRepository extends CrudRepository<Sample, Long> {

    List<Sample> findByPatientId(Long patientId);
}
