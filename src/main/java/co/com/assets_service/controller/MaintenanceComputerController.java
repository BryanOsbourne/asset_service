package co.com.assets_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.dto.MaintenanceComputerCreateDTO;
import co.com.assets_service.dto.MaintenanceComputerUpdateDTO;
import co.com.assets_service.dto.MaintenanceComputerResponseDTO;
import co.com.assets_service.service.MaintenanceComputerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/maintenance-computer")
public class MaintenanceComputerController {

    private final MaintenanceComputerService maintenanceComputerService;

    private final Logger LOGGER = LoggerFactory.getLogger(MaintenanceComputerController.class);

    @GetMapping(
            value = "/findAll",
            params = {"page", "size", "maintenancePlanComputerId"}
    )
    public ResponseEntity<Page<MaintenanceComputerResponseDTO>> getAllMaintenancesByMaintenancePlanComputerId(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam Long maintenancePlanComputerId
    ) {
        LOGGER.info("MaintenanceController - getAllMaintenancesByMaintenancePlanComputerId - page: {}, size: {}, userId: {}", page, size, maintenancePlanComputerId);
        return new ResponseEntity<>(maintenanceComputerService.findAllMaintenancesByMaintenancePlanComputerId(page, size, maintenancePlanComputerId), HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<MaintenanceComputerResponseDTO> createMaintenance(@Valid @RequestBody MaintenanceComputerCreateDTO maintenanceComputerCreateDTO) {
        LOGGER.info("MaintenanceController - createMaintenance - maintenanceCreateDTO: {}", maintenanceComputerCreateDTO);
        return new ResponseEntity<>(maintenanceComputerService.create(maintenanceComputerCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<MaintenanceComputerResponseDTO> updateMaintenance(@Valid @RequestBody MaintenanceComputerUpdateDTO maintenanceComputerUpdateDTO) {
        LOGGER.info("MaintenanceController - updateMaintenance - maintenanceUpdateDTO: {}", maintenanceComputerUpdateDTO);
        return new ResponseEntity<>(maintenanceComputerService.update(maintenanceComputerUpdateDTO), HttpStatus.OK);
    }

}