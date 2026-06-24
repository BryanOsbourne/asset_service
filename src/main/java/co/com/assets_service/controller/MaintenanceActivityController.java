package co.com.assets_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.dto.MaintenanceActivityUpdateDTO;
import co.com.assets_service.dto.MaintenanceActivityCreateDTO;
import co.com.assets_service.dto.MaintenanceActivityResponseDTO;
import co.com.assets_service.service.MaintenanceActivityService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/maintenance-activity")
public class MaintenanceActivityController {

    private final MaintenanceActivityService maintenanceActivityService;

    private final Logger LOGGER = LoggerFactory.getLogger(MaintenanceActivityController.class);

    @GetMapping(
            value = "/findAll",
            params = {"page", "size", "maintenanceComputerId"}
    )
    public ResponseEntity<Page<MaintenanceActivityResponseDTO>> findAllByMaintenanceComputerId(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam Long maintenanceComputerId
    ) {
        LOGGER.info("MaintenanceActivityController - findAllByMaintenanceComputerId - page: {}, size: {}, maintenanceComputerId: {}", page, size, maintenanceComputerId);
        return new ResponseEntity<>(maintenanceActivityService.findAllByMaintenanceComputerId(page, size, maintenanceComputerId), HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<MaintenanceActivityResponseDTO> createMaintenanceActivity(@Valid @RequestBody MaintenanceActivityCreateDTO maintenanceActivityCreateDTO) {
        LOGGER.info("MaintenanceActivityController - createMaintenanceActivity - maintenanceActivityCreateDTO: {}", maintenanceActivityCreateDTO);
        return new ResponseEntity<>(maintenanceActivityService.createMaintenanceActivity(maintenanceActivityCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<MaintenanceActivityResponseDTO> updateMaintenanceActivity(@Valid @RequestBody MaintenanceActivityUpdateDTO maintenanceActivityUpdateDTO) {
        LOGGER.info("MaintenanceActivityController - updateMaintenanceActivity - maintenanceActivityUpdateDTO: {}", maintenanceActivityUpdateDTO);
        return new ResponseEntity<>(maintenanceActivityService.updateMaintenanceActivity(maintenanceActivityUpdateDTO), HttpStatus.OK);
    }

}