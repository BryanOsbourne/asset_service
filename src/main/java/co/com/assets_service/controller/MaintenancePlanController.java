package co.com.assets_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.dto.MaintenancePlanUpdateDTO;
import co.com.assets_service.dto.MaintenancePlanCreateDTO;
import co.com.assets_service.dto.MaintenancePlanResponseDTO;
import co.com.assets_service.service.MaintenancePlanService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/maintenance-plan")
public class MaintenancePlanController {

    private final MaintenancePlanService maintenancePlanService;

    private final Logger LOGGER = LoggerFactory.getLogger(MaintenancePlanController.class);

    @GetMapping(
            value = "/findAll",
            params = {"page", "size"}
    )
    public ResponseEntity<Page<MaintenancePlanResponseDTO>> findAll(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return new ResponseEntity<>(maintenancePlanService.findAll(page, size), HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<MaintenancePlanResponseDTO> createMaintenancePlan(@Valid @RequestBody MaintenancePlanCreateDTO maintenancePlanCreateDTO) {
        LOGGER.info("MaintenancePlanController - createMaintenancePlan - maintenancePlanCreateDTO: {}", maintenancePlanCreateDTO);
        return new ResponseEntity<>(maintenancePlanService.createMaintenancePlan(maintenancePlanCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<MaintenancePlanResponseDTO> updateMaintenancePlan(@Valid @RequestBody MaintenancePlanUpdateDTO maintenancePlanUpdateDTO) {
        LOGGER.info("MaintenancePlanController - updateMaintenancePlan - maintenancePlanUpdateDTO: {}", maintenancePlanUpdateDTO);
        return new ResponseEntity<>(maintenancePlanService.updateMaintenancePlan(maintenancePlanUpdateDTO), HttpStatus.OK);
    }

    @PatchMapping(
            value = "/complete",
            params = {"id"}
    )
    public ResponseEntity<MaintenancePlanResponseDTO> completedMaintenancePlan(@RequestParam Long id) {
        LOGGER.info("MaintenancePlanComputerController - completedMaintenancePlan - id: {}", id);
        return new ResponseEntity<>(maintenancePlanService.close(id), HttpStatus.OK);
    }

}