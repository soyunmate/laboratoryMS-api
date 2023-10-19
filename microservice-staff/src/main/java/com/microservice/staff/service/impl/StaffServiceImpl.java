package com.microservice.staff.service.impl;

import com.microservice.staff.entities.Staff;
import com.microservice.staff.entities.enums.Area;
import com.microservice.staff.entities.enums.Charge;
import com.microservice.staff.repository.StaffRepository;
import com.microservice.staff.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements IStaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public Optional<Staff> findByUserId(Long userId) {
        return staffRepository.findByUserId(userId);
    }

    @Override
    public List<Staff> findByArea(Area area) {
        return staffRepository.findByArea(area);
    }

    @Override
    public List<Staff> findByCharge(Charge charge) {
        return staffRepository.findByCharge(charge);
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public List<Staff> findAll() {
        return (List<Staff>) staffRepository.findAll();
    }

    @Override
    public void save(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void deleteById(Long id) {
        staffRepository.deleteById(id);
    }
}
