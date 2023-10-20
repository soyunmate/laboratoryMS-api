package com.microservice.laboratory.service;

import com.microservice.laboratory.entities.Laboratory;
import com.microservice.laboratory.entities.enums.Area;

import java.util.List;
import java.util.Optional;

public interface ILaboratoryService {
    Optional<Laboratory> findByArea(Area area);
    Optional<Laboratory> findById(Long id);

    List<Laboratory>  findAll();

    void save(Laboratory laboratory);

    void deleteById(Long id);
}
