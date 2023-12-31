package com.microservice.samples.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.common.http.Response;
import com.microservice.common.mapper.ResponseMapper;
import com.microservice.samples.controller.dto.SampleDTO;
import com.microservice.samples.entities.Sample;
import com.microservice.samples.listeners.KafkaConsumerListener;
import com.microservice.samples.service.ISampleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/samples")
public class SampleController {
    @Autowired
    private ISampleService sampleService;
    private final ResponseMapper responseMapper = new ResponseMapper();

    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id) {
        Optional<Sample> sample = sampleService.findById(id);


        if (sample.isPresent()) {
            SampleDTO sampleDTO = new SampleDTO(sample.get());
            return ResponseEntity.ok(   responseMapper.toResponse(sampleDTO, "Sample Retrieved", "Sample", HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMapper.toResponse(null,
                "Sample not found",
                "Sample",
                HttpStatus.NOT_FOUND));
    }
    @GetMapping("/patient/{id}")
    public ResponseEntity<Response> findByPatientId(@PathVariable Long patientId) {
        List<SampleDTO> sampleDTOList = sampleService.findByPatientId(patientId).stream().map(SampleDTO::new).toList();
        return ResponseEntity.ok(responseMapper.toResponse(sampleDTOList, "Patient Samples Retrieved", "Sample", HttpStatus.OK));

    }
    @PostMapping()
    public ResponseEntity<Response> save(@Valid @RequestBody SampleDTO sampleDTO) {
            Sample sample = Sample.builder()
                    .patientId(sampleDTO.getPatientId())
                    .acceptanceDate(sampleDTO.getAcceptanceDate())
                    .collectionDate(sampleDTO.getCollectionDate())
                    .type(sampleDTO.getType())
                    .reporterId(sampleDTO.getReporterId())
                    .requestedExamsId(sampleDTO.getRequestedExamsId())
                    .reportingDate(sampleDTO.getReportingDate())
                    .receptionDate(sampleDTO.getReceptionDate())
                    .resultListId(sampleDTO.getResultListId())
                    .build();

            sampleService.save(sample);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toResponse(sampleDTO,"Sample Received","Sample",HttpStatus.OK));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @Valid @RequestBody SampleDTO sampleDTO) {
        Optional<Sample> sample = sampleService.findById(id);

        if(sample.isPresent()) {
            Sample updatedSample = Sample.builder()
                    .id(id)
                    .patientId(sampleDTO.getPatientId())
                    .acceptanceDate(sampleDTO.getAcceptanceDate())
                    .collectionDate(sampleDTO.getCollectionDate())
                    .type(sampleDTO.getType())
                    .reporterId(sampleDTO.getReporterId())
                    .requestedExamsId(sampleDTO.getRequestedExamsId())
                    .reportingDate(sampleDTO.getReportingDate())
                    .receptionDate(sampleDTO.getReceptionDate())
                    .build();

            sampleService.save(updatedSample);

            return ResponseEntity.ok(responseMapper.toResponse(sampleDTO,"Sample Updated","Sample", HttpStatus.OK));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMapper.toResponse(null,
                "Sample not found",
                "Sample",
                HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id) {
        Optional<Sample> sampleToDelete = sampleService.findById(id);

        if (sampleToDelete.isPresent()) {
            sampleService.deleteById(id);
            return ResponseEntity.ok(responseMapper.toResponse(null,"Sample Deleted","Sample", HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMapper.toResponse(null,
                "Sample not found",
                "Sample",
                HttpStatus.NOT_FOUND));
    }


    /*@KafkaListener(topics = {"result_emited"}, groupId = "laboratoryMS-group")
    public void resultEmitedListener(String message) {

        LOGGER.info("Message received in Controller: "+message);

        try {

            Map<String, Long> mapMessage = objectMapper.readValue(message, Map.class);
            Optional<Sample> sample = sampleService.findById(mapMessage.get("sampleId"));

            if (sample.isPresent()) {
                Sample updatedSample = sample.get();
                Set<Long> resultIds = updatedSample.getResultListId();
                resultIds.add(mapMessage.get("resultId"));

                updatedSample.setResultListId(resultIds);
                sampleService.save(updatedSample);

            }
        } catch (JsonProcessingException e) {
            LOGGER.error("Error:",e);
        }

    }*/

}
