package com.microservice.patients.controller;

import com.microservice.common.http.Response;
import com.microservice.common.mapper.ResponseMapper;
import com.microservice.patients.controller.dto.PatientDTO;
import com.microservice.patients.entities.Patient;
import com.microservice.patients.service.IPatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private IPatientService patientService;

    private final ResponseMapper responseMapper = new ResponseMapper();

    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id) {
        Optional<Patient> optionalPatient = patientService.findById(id);

        if (optionalPatient.isPresent()) {
            PatientDTO patientDTO = new PatientDTO(optionalPatient.get());
            return ResponseEntity.ok(   responseMapper.toResponse(patientDTO, "Patient Retrieved", "Patient", HttpStatus.OK));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMapper.toResponse(null,
                "Patient not found",
                "Patient",
                HttpStatus.NOT_FOUND));

    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<Response> findByRut(@PathVariable String rut) {
        Optional<Patient> optionalPatient = patientService.findByRut(rut);

        if (optionalPatient.isPresent()) {
            PatientDTO patientDTO = new PatientDTO(optionalPatient.get());
            return ResponseEntity.ok(   responseMapper.toResponse(patientDTO, "Patient Retrieved", "Patient", HttpStatus.OK));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMapper.toResponse(null,
                "Patient not found",
                "Patient",
                HttpStatus.NOT_FOUND));

    }
    @GetMapping
    public ResponseEntity<Response> findAll() {
        List<PatientDTO> patientDTOList = patientService.findAll()
                .stream()
                .map(PatientDTO::new).toList();
        return ResponseEntity.ok(   responseMapper.toResponse(patientDTOList, "Patients Retrieved", "Patient", HttpStatus.OK));

    }
    @PostMapping
    public ResponseEntity<Response> save(@Valid @RequestBody PatientDTO patientDTO) {
            Patient patient = Patient.builder()
                    .firstName(patientDTO.getFirstName())
                    .lastName(patientDTO.getLastName())
                    .rut(patientDTO.getRut())
                    .email(patientDTO.getEmail())
                    .phone(patientDTO.getPhone())
                    .sampleIds(patientDTO.getSampleIds())
                    .build();

            patientService.save(patient);

        return ResponseEntity.ok(   responseMapper.toResponse(patientDTO, "Patients Saved", "Patient", HttpStatus.OK));

    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @Valid @RequestBody PatientDTO patientDTO) {
        Optional<Patient> optionalPatient = patientService.findById(id);

        if (optionalPatient.isPresent()) {
            Patient updatedPatient = Patient.builder()
                    .id(id)
                    .firstName(patientDTO.getFirstName())
                    .lastName(patientDTO.getLastName())
                    .rut(patientDTO.getRut())
                    .email(patientDTO.getEmail())
                    .phone(patientDTO.getPhone())
                    .sampleIds(patientDTO.getSampleIds())
                    .build();

            patientService.save(updatedPatient);
            return ResponseEntity.ok(   responseMapper.toResponse(patientDTO, "Patient Updated", "Patient", HttpStatus.OK));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMapper.toResponse(null,
                "Patient not found",
                "Patient",
                HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id) {
        Optional<Patient> optionalPatient = patientService.findById(id);

        if (optionalPatient.isPresent()) {
            patientService.deleteById(id);
            return ResponseEntity.ok(   responseMapper.toResponse(null, "Patient Deleted", "Patient", HttpStatus.OK));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMapper.toResponse(null,
                "Patient not found",
                "Patient",
                HttpStatus.NOT_FOUND));
    }
}
