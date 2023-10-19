package com.microservice.staff.repository;

import com.microservice.staff.entities.Staff;
import com.microservice.staff.entities.enums.Area;
import com.microservice.staff.entities.enums.Charge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {
    Optional<Staff> findByUserId(Long userId);

    List<Staff> findByArea(Area area);

    List<Staff> findByCharge(Charge charge);
}
