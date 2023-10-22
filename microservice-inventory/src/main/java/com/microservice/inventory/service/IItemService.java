package com.microservice.inventory.service;

import com.microservice.inventory.entities.Item;
import com.microservice.inventory.entities.Vendor;

import java.util.List;
import java.util.Optional;

public interface IItemService {
    Optional<Item> findById(Long id);

    List<Item> findAll();

    void save(Item item);

    void deleteById(Long id);
}
