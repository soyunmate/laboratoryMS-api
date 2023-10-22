package com.microservice.inventory.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microservice.inventory.entities.Item;
import com.microservice.inventory.entities.Vendor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorDTO {
    private Long id;
    private String name;
    private String catalogUrl;
    private List<Long> itemIdList;
    private String email;

    public VendorDTO(Vendor vendor) {
        this.id = vendor.getId();
        this.name = vendor.getName();
        this.catalogUrl = vendor.getCatalogUrl();
        this.itemIdList = vendor.getItemList().stream().map(Item::getId).toList();
        this.email = vendor.getEmail();
    }
}
