package com.microservice.laboratory.controller;

import com.microservice.common.http.Response;
import com.microservice.common.mapper.ResponseMapper;
import com.microservice.laboratory.controller.dto.LabDTO;
import com.microservice.laboratory.entities.Laboratory;
import com.microservice.laboratory.entities.enums.Area;
import com.microservice.laboratory.service.ILaboratoryService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/laboratory")
public class LaboratoryController {
    @Autowired
    private ILaboratoryService labService;

    private final ResponseMapper responseMapper = new ResponseMapper();

    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id) {
        Optional<Laboratory> optionalLaboratory = labService.findById(id);

        if(optionalLaboratory.isPresent()) {
            LabDTO labDTO = new LabDTO(optionalLaboratory.get());

            return ResponseEntity.ok(responseMapper.toResponse(labDTO,"Laboratory Info Retrieved","Laboratory", HttpStatus.OK));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Laboratory Not Found","Laboratory",HttpStatus.NOT_FOUND));

    }
    @GetMapping("/areas/{area}")
    public ResponseEntity<Response> findByArea(@PathVariable String area) {
        Optional<Laboratory> optionalLaboratory = labService.findByArea(Area.valueOf(area));

        if(optionalLaboratory.isPresent()) {
            LabDTO labDTO = new LabDTO(optionalLaboratory.get());

            return ResponseEntity.ok(responseMapper.toResponse(labDTO,"Laboratory Info Retrieved","Laboratory", HttpStatus.OK));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Laboratory Not Found","Laboratory",HttpStatus.NOT_FOUND));

    }
    @PostMapping
    public ResponseEntity<Response> save(@Valid @RequestBody LabDTO labDTO) {
        Laboratory laboratory = Laboratory.builder()
                .name(labDTO.getName())
                .area(Area.valueOf(labDTO.getArea()))
                .examsIds(labDTO.getExamsIds())
                .staffIds(labDTO.getStaffIds())
                .leadTechId(labDTO.getLeadTechId())
                .build();

        labService.save(laboratory);

        return ResponseEntity.ok(responseMapper.toResponse(labDTO,"Laboratory Created","Laboratory", HttpStatus.OK));

    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @Valid @RequestBody LabDTO labDTO) {
        Optional<Laboratory> optionalLaboratory = labService.findById(id);

        if(optionalLaboratory.isPresent()) {
            Laboratory updatedLab = Laboratory.builder()
                    .id(id)
                    .name(labDTO.getName())
                    .area(Area.valueOf(labDTO.getArea()))
                    .examsIds(labDTO.getExamsIds())
                    .staffIds(labDTO.getStaffIds())
                    .leadTechId(labDTO.getLeadTechId())
                    .build();

            return ResponseEntity.ok(responseMapper.toResponse(labDTO,"Laboratory Info Updated","Laboratory", HttpStatus.OK));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Laboratory Not Found","Laboratory",HttpStatus.NOT_FOUND));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id ) {
        Optional<Laboratory> optionalLaboratory = labService.findById(id);

        if(optionalLaboratory.isPresent()) {
            labService.deleteById(id);

            return ResponseEntity.ok(responseMapper.toResponse(null,"Laboratory Deleted","Laboratory", HttpStatus.OK));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Laboratory Not Found","Laboratory",HttpStatus.NOT_FOUND));

    }

}
