package com.microservice.laboratory.service.impl;

import com.microservice.laboratory.entities.Laboratory;
import com.microservice.laboratory.entities.enums.Area;
import com.microservice.laboratory.repository.LaboratoryRepository;
import com.microservice.laboratory.service.ILaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LaboratoryServiceImpl implements ILaboratoryService {
    @Autowired
    private LaboratoryRepository laboratoryRepository;
    @Override
    public Optional<Laboratory> findByArea(Area area) {
        return laboratoryRepository.findByArea(area);
    }

    @Override
    public Optional<Laboratory> findById(Long id) {
        return laboratoryRepository.findById(id);
    }

    @Override
    public List<Laboratory> findAll() {
        return (List<Laboratory>) laboratoryRepository.findAll();
    }

    @Override
    public void save(Laboratory laboratory) {
        laboratoryRepository.save(laboratory);
    }

    @Override
    public void deleteById(Long id) {
        laboratoryRepository.deleteById(id);
    }
}
