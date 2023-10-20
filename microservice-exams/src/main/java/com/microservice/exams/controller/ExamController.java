package com.microservice.exams.controller;

import com.microservice.common.http.Response;
import com.microservice.common.mapper.ResponseMapper;
import com.microservice.exams.controller.dto.ExamDTO;
import com.microservice.exams.entities.Exam;
import com.microservice.exams.service.IExamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/api/v1/exams")
public class ExamController {

    @Autowired
    private IExamService examService;

    private final ResponseMapper responseMapper = new ResponseMapper();

    @GetMapping("/code/{code}")
    public ResponseEntity<Response> findByCode(@PathVariable String code){
        Optional<Exam> optionalExam = examService.findByCode(code);

        if (optionalExam.isPresent()) {
            ExamDTO examDTO = new ExamDTO(optionalExam.get());
            return ResponseEntity.ok(responseMapper.toResponse(examDTO,"Exam Retrieved","Exam", HttpStatus.OK));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Exam Not Found","Exam",HttpStatus.NOT_FOUND));

    }
    @GetMapping("/laboratory/{labId}")
    public ResponseEntity<Response> findByLabId(@PathVariable Long labId){
        List<ExamDTO> examDTOList = examService.findByLabId(labId)
                .stream()
                .map(ExamDTO::new)
                .toList();

            return ResponseEntity.ok(responseMapper.toResponse(examDTOList,"Laboratory Exams Retrieved","Exam", HttpStatus.OK));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id){
        Optional<Exam> optionalExam = examService.findById(id);

        if (optionalExam.isPresent()) {
            ExamDTO examDTO = new ExamDTO(optionalExam.get());
            return ResponseEntity.ok(responseMapper.toResponse(examDTO,"Exam Retrieved","Exam", HttpStatus.OK));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Exam Not Found","Exam",HttpStatus.NOT_FOUND));

    }
    @GetMapping
    public ResponseEntity<Response> findAll(){

        List<ExamDTO> examDTOList = examService.findAll()
                .stream()
                .map(ExamDTO::new)
                .toList();

        return ResponseEntity.ok(responseMapper.toResponse(examDTOList,"Laboratory Exams Retrieved","Exam", HttpStatus.OK));

    }
    @PostMapping
    public ResponseEntity<Response> save(@Valid @RequestBody ExamDTO examDTO){
        Exam exam = Exam.builder()
                .code(examDTO.getCode())
                .name(examDTO.getName())
                .labArea(examDTO.getLabArea())
                .labId(examDTO.getLabId())
                .indications(examDTO.getIndications())
                .sampleType(examDTO.getSampleType())
                .build();

        examService.save(exam);
        return ResponseEntity.ok(responseMapper.toResponse(examDTO,"Exam Saved","Exam", HttpStatus.OK));

    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @Valid ExamDTO examDTO){
        Optional<Exam> optionalExam = examService.findById(id);

        if (optionalExam.isPresent()) {
            Exam updatedExam = Exam.builder()
                    .id(id)
                    .code(examDTO.getCode())
                    .name(examDTO.getName())
                    .labArea(examDTO.getLabArea())
                    .labId(examDTO.getLabId())
                    .indications(examDTO.getIndications())
                    .sampleType(examDTO.getSampleType())
                    .build();

            return ResponseEntity.ok(responseMapper.toResponse(examDTO,"Exam Updated","Exam", HttpStatus.OK));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Exam Not Found","Exam",HttpStatus.NOT_FOUND));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id){
        Optional<Exam> optionalExam = examService.findById(id);

        if (optionalExam.isPresent()) {
            examService.deleteById(id);
            return ResponseEntity.ok(responseMapper.toResponse(null,"Exam Deleted","Exam", HttpStatus.OK));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Exam Not Found","Exam",HttpStatus.NOT_FOUND));

    }



}
