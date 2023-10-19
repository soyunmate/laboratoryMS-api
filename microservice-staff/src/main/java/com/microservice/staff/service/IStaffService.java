package com.microservice.staff.service;

import com.microservice.staff.entities.Staff;
import com.microservice.staff.entities.enums.Area;
import com.microservice.staff.entities.enums.Charge;

import java.util.List;
import java.util.Optional;

public interface IStaffService {
    Optional<Staff> findByUserId(Long userId);

    List<Staff> findByArea(Area area);

    List<Staff> findByCharge(Charge charge);

    Optional<Staff> findById(Long id);

    List<Staff> findAll();

    void save(Staff staff);

    void deleteById(Long id);
}
