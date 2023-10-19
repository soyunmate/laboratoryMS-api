package com.microservice.staff.controller;
import com.microservice.common.http.Response;
import com.microservice.common.mapper.ResponseMapper;
import com.microservice.staff.controller.dto.StaffDTO;
import com.microservice.staff.entities.Staff;
import com.microservice.staff.entities.enums.Area;
import com.microservice.staff.entities.enums.Charge;
import com.microservice.staff.service.IStaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
    @Autowired
    private IStaffService staffService;
    private final ResponseMapper responseMapper = new ResponseMapper();
    @GetMapping
    public ResponseEntity<Response> findAll() {
        List<StaffDTO> staffDTOList = staffService.findAll().stream().map(StaffDTO::new).toList();
        return ResponseEntity.ok(responseMapper.toResponse(staffDTOList,"All Staff Retrieved", "Staff", HttpStatus.OK));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response> findById(@PathVariable Long id) {
        Optional<Staff> optionalStaff = staffService.findById(id);

        if (optionalStaff.isPresent()) {
            StaffDTO staffDTO = new StaffDTO(optionalStaff.get());

            return ResponseEntity.ok(responseMapper.toResponse(staffDTO,"Staff Member Retrieved","Staff",HttpStatus.OK));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Staff Member Not Found","Staff",HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Response> findByUserId(@PathVariable Long id) {
        Optional<Staff> optionalStaff = staffService.findByUserId(id);

        if (optionalStaff.isPresent()) {
            StaffDTO staffDTO = new StaffDTO(optionalStaff.get());

            return ResponseEntity.ok(responseMapper.toResponse(staffDTO,"Staff Member Retrieved","Staff",HttpStatus.OK));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Staff Member Not Found","Staff",HttpStatus.NOT_FOUND));
    }

    @GetMapping("/areas/{area}")
    public ResponseEntity<Response> findByArea(@PathVariable String area) {
        List<StaffDTO> staffDTOList = staffService.findByArea(Area.valueOf(area))
                .stream()
                .map(StaffDTO::new)
                .toList();

        return ResponseEntity.ok(responseMapper.toResponse(staffDTOList,area+"´s Staff retrieved","Staff",HttpStatus.OK));
     }

    @GetMapping("/charges/{charge}")
    public ResponseEntity<Response> findByCharge(@PathVariable String charge) {
        List<StaffDTO> staffDTOList = staffService.findByCharge(Charge.valueOf(charge))
                .stream()
                .map(StaffDTO::new)
                .toList();

        return ResponseEntity.ok(responseMapper.toResponse(staffDTOList,charge+" Staff retrieved","Staff",HttpStatus.OK));
    }
    @PostMapping
    public ResponseEntity<Response> save(@Valid @RequestBody StaffDTO staffDTO) {
        Staff staff = Staff.builder()
                .userId(staffDTO.getUserId())
                .firstName(staffDTO.getFirstName())
                .lastName(staffDTO.getLastName())
                .rut(staffDTO.getRut())
                .area(Area.valueOf(staffDTO.getArea()))
                .charge(Charge.valueOf(staffDTO.getCharge()))
                .build();
        staffService.save(staff);

        return ResponseEntity.ok(responseMapper.toResponse(staffDTO,"Staff Registered","Staff",HttpStatus.OK));

    }
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateById(@PathVariable Long id, @Valid @RequestBody StaffDTO staffDTO){
        Optional<Staff> optionalStaff = staffService.findById(id);

        if(optionalStaff.isPresent()) {
            Staff updatedStaff = Staff.builder()
                    .id(id)
                    .userId(staffDTO.getUserId())
                    .firstName(staffDTO.getFirstName())
                    .lastName(staffDTO.getLastName())
                    .rut(staffDTO.getRut())
                    .area(Area.valueOf(staffDTO.getArea()))
                    .charge(Charge.valueOf(staffDTO.getCharge()))
                    .build();

            staffService.save(updatedStaff);
            return ResponseEntity.ok(responseMapper.toResponse(staffDTO,"Staff Updated","Staff",HttpStatus.OK));

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseMapper.toResponse(null,"Staff Member Not Found","Staff",HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id) {
        Optional<Staff> optionalStaff = staffService.findById(id);
        if(optionalStaff.isPresent()) {
            staffService.deleteById(id);
            return ResponseEntity.ok(responseMapper.toResponse(null,"Staff Member Deleted","Staff", HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMapper.toResponse(null,
                "Staff Member Not Found",
                "Staff",
                HttpStatus.NOT_FOUND));
    }


}
