package com.microservice.inventory.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservice.inventory.entities.Item;
import com.microservice.inventory.entities.Vendor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {
    private Long id;
    private String catalogNumber;

    private Long vendorId;

    private String type;

    private int stock;

    public ItemDTO(Item item) {
        this.id = item.getId();
        this.catalogNumber = item.getCatalogNumber();
        this.vendorId = item.getVendor().getId();
        this.type = item.getType();
        this.stock = item.getStock();
    }
}
