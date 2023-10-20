package com.microservice.results.controller;

import com.microservice.common.http.Response;
import com.microservice.common.mapper.ResponseMapper;
import com.microservice.results.controller.dto.ResultDTO;
import com.microservice.results.entities.Result;
import com.microservice.results.service.IResultService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/results")
public class ResultController {
    @Autowired
    private IResultService resultService;

    private final ResponseMapper responseMapper = new ResponseMapper();
    @GetMapping("/patients/{patientId}")
    public ResponseEntity<Response> findByPatientId(@PathVariable Long patientId) {
        List<ResultDTO> resultDTOList = resultService.findByPatientId(patientId)
                .stream()
                .map(ResultDTO::new)
                .toList();

        return ResponseEntity.ok(responseMapper.toResponse(resultDTOList,"Patient´s Results Retrieved", "Result", HttpStatus.OK));

    }

    @GetMapping("/samples/{sampleId}")
    public ResponseEntity<Response> findBySampleId(@PathVariable Long sampleId) {
        List<ResultDTO> resultDTOList = resultService.findBySampleId(sampleId)
                .stream()
                .map(ResultDTO::new)
                .toList();

        return ResponseEntity.ok(responseMapper.toResponse(resultDTOList,"Sample´s Results Retrieved", "Result", HttpStatus.OK));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id) {
        Optional<Result> optionalResult = resultService.findById(id);

        if (optionalResult.isPresent()) {
            ResultDTO resultDTO = new ResultDTO(optionalResult.get());
            return ResponseEntity.ok(responseMapper.toResponse(resultDTO,
                    "Result Retrieves",
                    "Result",
                    HttpStatus.OK));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Result Not Found","Result",HttpStatus.NOT_FOUND));

    }
    @PostMapping
    public ResponseEntity<Response> save(@Valid @RequestBody ResultDTO resultDTO) {
        Result result = Result.builder()
                .examId(resultDTO.getExamId())
                .patientId(resultDTO.getPatientId())
                .sampleId(resultDTO.getSampleId())
                .date(resultDTO.getDate())
                .resultData(resultDTO.getResultData())
                .url(resultDTO.getUrl())
                .build();

        resultService.save(result);

        return ResponseEntity.ok(responseMapper.toResponse(resultDTO,
                "Result Saved",
                "Result",
                HttpStatus.OK));

    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @Valid @RequestBody ResultDTO resultDTO) {
        Optional<Result> optionalResult = resultService.findById(id);
        if (optionalResult.isPresent()) {
            Result updatedResult = Result.builder()
                    .id(id)
                    .examId(resultDTO.getExamId())
                    .patientId(resultDTO.getPatientId())
                    .sampleId(resultDTO.getSampleId())
                    .date(resultDTO.getDate())
                    .resultData(resultDTO.getResultData())
                    .url(resultDTO.getUrl())
                    .build();

            resultService.save(updatedResult);

            return ResponseEntity.ok(responseMapper.toResponse(resultDTO,
                    "Result Updated",
                    "Result",
                    HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Result Not Found","Result",HttpStatus.NOT_FOUND));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id){
        Optional<Result> optionalResult = resultService.findById(id);

        if (optionalResult.isPresent()) {
            resultService.deleteById(id);

            return ResponseEntity.ok(responseMapper.toResponse(null,
                    "Result Deleted",
                    "Result",
                    HttpStatus.OK));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Result Not Found","Result",HttpStatus.NOT_FOUND));

    }




}
