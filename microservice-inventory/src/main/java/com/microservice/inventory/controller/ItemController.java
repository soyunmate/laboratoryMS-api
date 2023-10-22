package com.microservice.inventory.controller;

import com.microservice.common.http.Response;
import com.microservice.common.mapper.ResponseMapper;
import com.microservice.inventory.controller.dto.ItemDTO;
import com.microservice.inventory.entities.Item;
import com.microservice.inventory.entities.Vendor;
import com.microservice.inventory.service.IItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    @Autowired
    private IItemService itemService;

    private final ResponseMapper responseMapper = new ResponseMapper();
    @GetMapping
    public ResponseEntity<Response> findAll() {
        List<ItemDTO> itemDTOList = itemService.findAll().stream().map(ItemDTO::new).toList();

        return ResponseEntity.ok(responseMapper.toResponse(itemDTOList,"Items Retrieved","Item", HttpStatus.OK));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id) {
        Optional<Item> optionalItem = itemService.findById(id);

        if(optionalItem.isPresent()) {
            ItemDTO itemDTO = new ItemDTO(optionalItem.get());
            return ResponseEntity.ok(responseMapper.toResponse(itemDTO,"Item Retrieved","Item", HttpStatus.OK));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Item Not Found","Item",HttpStatus.NOT_FOUND));

    }
    @PostMapping
    public ResponseEntity<Response> save(@Valid @RequestBody ItemDTO itemDTO) {
        Item item = Item.builder()
                .catalogNumber(itemDTO.getCatalogNumber())
                .vendor(Vendor.builder().id(itemDTO.getVendorId()).build())
                .type(itemDTO.getType())
                .stock(itemDTO.getStock())
                .build();

        itemService.save(item);

        return ResponseEntity.ok(responseMapper.toResponse(itemDTO,"Item Saved","Item", HttpStatus.OK));

    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @Valid @RequestBody ItemDTO itemDTO) {
        Optional<Item> optionalItem = itemService.findById(id);

        if(optionalItem.isPresent()) {
            Item updatedItem = Item.builder()
                    .id(id)
                    .catalogNumber(itemDTO.getCatalogNumber())
                    .vendor(Vendor.builder().id(itemDTO.getVendorId()).build())
                    .type(itemDTO.getType())
                    .stock(itemDTO.getStock())
                    .build();
            itemService.save(updatedItem);

            return ResponseEntity.ok(responseMapper.toResponse(itemDTO,"Item Updated","Item", HttpStatus.OK));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Item Not Found","Item",HttpStatus.NOT_FOUND));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id) {
        Optional<Item> optionalItem = itemService.findById(id);

        if(optionalItem.isPresent()) {
            itemService.deleteById(id);
            return ResponseEntity.ok(responseMapper.toResponse(null,"Item Deleted","Item", HttpStatus.OK));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Item Not Found","Item",HttpStatus.NOT_FOUND));

    }


}
