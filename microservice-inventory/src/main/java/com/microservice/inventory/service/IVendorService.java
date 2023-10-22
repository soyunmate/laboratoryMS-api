package com.microservice.inventory.service;

import com.microservice.inventory.entities.Vendor;

import java.util.List;
import java.util.Optional;

public interface IVendorService {

    Optional<Vendor> findById(Long id);

    List<Vendor> findAll();

    void save(Vendor vendor);

    void deleteById(Long id);
}
