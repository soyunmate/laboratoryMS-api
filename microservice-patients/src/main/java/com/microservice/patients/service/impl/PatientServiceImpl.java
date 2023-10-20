package com.microservice.patients.service.impl;

import com.microservice.patients.entities.Patient;
import com.microservice.patients.repository.PatientRepository;
import com.microservice.patients.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PatientServiceImpl implements IPatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public Optional<Patient> findByRut(String rut) {
        return patientRepository.findByRut(rut);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public List<Patient> findAll() {
        return (List<Patient>) patientRepository.findAll();
    }

    @Override
    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }
}
