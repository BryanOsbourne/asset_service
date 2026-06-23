package co.com.assets_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.dto.MaintenancePlanComputerCreateDTO;
import co.com.assets_service.dto.MaintenancePlanComputerUpdateDTO;
import co.com.assets_service.dto.MaintenancePlanComputerResponseDTO;
import co.com.assets_service.service.MaintenancePlanComputerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/maintenance-plan-computer")
public class MaintenancePlanComputerController {

    private final MaintenancePlanComputerService maintenancePlanComputerService;

    private final Logger LOGGER = LoggerFactory.getLogger(MaintenancePlanComputerController.class);

    @GetMapping(
            value = "/findAll",
            params = {"page", "size", "maintenancePlanId"}
    )
    public ResponseEntity<Page<MaintenancePlanComputerResponseDTO>> findAllByMaintenancePlanId(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam Long maintenancePlanId
    ) {
        LOGGER.info("MaintenancePlanComputerController - findAllByMaintenancePlanId - page: {}, size: {}, userId: {}", page, size, maintenancePlanId);
        return new ResponseEntity<>(maintenancePlanComputerService.findAllByMaintenancePlanId(page, size, maintenancePlanId), HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<MaintenancePlanComputerResponseDTO> createMaintenancePlanComputer(@Valid @RequestBody MaintenancePlanComputerCreateDTO maintenancePlanComputerCreateDTO) {
        LOGGER.info("MaintenancePlanComputerController - createMaintenancePlanComputer - maintenancePlanComputerCreateDTO: {}", maintenancePlanComputerCreateDTO);
        return new ResponseEntity<>(maintenancePlanComputerService.create(maintenancePlanComputerCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<MaintenancePlanComputerResponseDTO> updateMaintenancePlanComputer(@Valid @RequestBody MaintenancePlanComputerUpdateDTO maintenancePlanComputerUpdateDTO) {
        LOGGER.info("MaintenancePlanComputerController - updateMaintenancePlanComputer - maintenancePlanComputerUpdateDTO: {}", maintenancePlanComputerUpdateDTO);
        return new ResponseEntity<>(maintenancePlanComputerService.update(maintenancePlanComputerUpdateDTO), HttpStatus.OK);
    }

    @DeleteMapping(
            value = "/delete",
            params = "id"
    )
    public ResponseEntity<String> deleteMaintenancePlanComputer(@RequestParam Long id) {
        LOGGER.info("MaintenancePlanComputerController - deleteMaintenancePlanComputer - id: {}", id);
        maintenancePlanComputerService.delete(id);
        return new ResponseEntity<>("Registry deleted successfully", HttpStatus.OK);
    }

}