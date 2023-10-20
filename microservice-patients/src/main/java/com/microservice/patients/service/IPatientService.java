package com.microservice.patients.service;

import com.microservice.patients.entities.Patient;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    Optional<Patient> findByRut(String rut);
    Optional<Patient> findById(Long id);

    List<Patient> findAll();

    void save(Patient patient);

    void deleteById(Long id);
}
