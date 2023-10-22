package com.microservice.inventory.controller;

import com.microservice.common.http.Response;
import com.microservice.common.mapper.ResponseMapper;
import com.microservice.inventory.controller.dto.VendorDTO;
import com.microservice.inventory.entities.Item;
import com.microservice.inventory.entities.Vendor;
import com.microservice.inventory.service.IVendorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inventory/vendors")
public class VendorController {
    @Autowired
    private IVendorService vendorService;

    private final ResponseMapper responseMapper = new ResponseMapper();

    @GetMapping
    public ResponseEntity<Response> findAll() {
        List<VendorDTO> vendorDTOList = vendorService.findAll().stream().map(VendorDTO::new).toList();

        return ResponseEntity.ok(responseMapper.toResponse(vendorDTOList,"Vendors Retrieved","Vendor", HttpStatus.OK));


    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id) {
        Optional<Vendor> optionalVendor = vendorService.findById(id);

        if(optionalVendor.isPresent()) {
            VendorDTO vendorDTO = new VendorDTO(optionalVendor.get());
            return ResponseEntity.ok(responseMapper.toResponse(vendorDTO,"Vendor Retrieved","Vendor", HttpStatus.OK));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Vendor Not Found","Vendor",HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<Response> save(@Valid @RequestBody VendorDTO vendorDTO) {
        Vendor vendor = Vendor.builder()
                .name(vendorDTO.getName())
                .catalogUrl(vendorDTO.getCatalogUrl())
                .email(vendorDTO.getEmail())
                .itemList(vendorDTO.getItemIdList().stream().map(id -> Item.builder().id(id).build()).toList())
                .build();

        vendorService.save(vendor);
        return ResponseEntity.ok(responseMapper.toResponse(vendorDTO,"Vendor Saved","Vendor", HttpStatus.OK));

    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @Valid @RequestBody VendorDTO vendorDTO) {
        Optional<Vendor> optionalVendor = vendorService.findById(id);

        if(optionalVendor.isPresent()) {
            Vendor updatedVendor = Vendor.builder()
                    .id(id)
                    .name(vendorDTO.getName())
                    .catalogUrl(vendorDTO.getCatalogUrl())
                    .email(vendorDTO.getEmail())
                    .itemList(vendorDTO.getItemIdList().stream().map(vid -> Item.builder().id(vid).build()).toList())
                    .build();

            vendorService.save(updatedVendor);
            return ResponseEntity.ok(responseMapper.toResponse(vendorDTO,"Vendor Updated","Vendor", HttpStatus.OK));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Vendor Not Found","Vendor",HttpStatus.NOT_FOUND));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id) {
        Optional<Vendor> optionalVendor = vendorService.findById(id);

        if(optionalVendor.isPresent()) {
            vendorService.deleteById(id);
            return ResponseEntity.ok(responseMapper.toResponse(null,"Vendor Deleted","Vendor", HttpStatus.OK));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Vendor Not Found","Vendor",HttpStatus.NOT_FOUND));

    }

}
