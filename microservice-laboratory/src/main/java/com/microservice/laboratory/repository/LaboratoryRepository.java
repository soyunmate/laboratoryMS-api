package com.microservice.laboratory.repository;

import com.microservice.laboratory.entities.Laboratory;
import com.microservice.laboratory.entities.enums.Area;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaboratoryRepository extends CrudRepository<Laboratory, Long> {
    Optional<Laboratory> findByArea(Area area);
}
