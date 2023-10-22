package com.microservice.inventory.service.impl;

import com.microservice.inventory.entities.Vendor;
import com.microservice.inventory.repository.VendorRepository;
import com.microservice.inventory.service.IVendorService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VendorServiceImpl implements IVendorService {
    @Autowired
    private VendorRepository vendorRepository;


    @Override
    public Optional<Vendor> findById(Long id) {
        return vendorRepository.findById(id);
    }

    @Override
    public List<Vendor> findAll() {
        return (List<Vendor>) vendorRepository.findAll();
    }

    @Override
    public void save(Vendor vendor) {
        vendorRepository.save(vendor);
    }

    @Override
    public void deleteById(Long id) {
        vendorRepository.deleteById(id);
    }
}
