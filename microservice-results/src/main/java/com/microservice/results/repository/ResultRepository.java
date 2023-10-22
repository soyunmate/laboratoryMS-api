package com.microservice.results.repository;

import com.microservice.results.entities.Result;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ResultRepository extends CrudRepository<Result, Long> {
    List<Result> findByPatientId(Long patientId);
    List<Result> findBySampleId(Long sampleId);

}
