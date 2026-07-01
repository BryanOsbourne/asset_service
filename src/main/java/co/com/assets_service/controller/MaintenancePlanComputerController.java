package co.com.assets_service.controller;

import org.slf4j.Logger;
import java.time.LocalDateTime;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.enums.MaintenancePlanningState;
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

    @GetMapping(
            value = "/findAll",
            params = {"page", "size", "state"}
    )
    public ResponseEntity<Page<MaintenancePlanComputerResponseDTO>> findAllByState(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam MaintenancePlanningState maintenancePlanningState
    ) {
        LOGGER.info("MaintenancePlanComputerController - findAllByState - page: {}, size: {}, maintenancePlanningState: {}", page, size, maintenancePlanningState);
        return new ResponseEntity<>(maintenancePlanComputerService.findAllByState(page, size, maintenancePlanningState), HttpStatus.OK);
    }

    @GetMapping(
            value = "/findAll",
            params = {"page", "size", "computerId"}
    )
    public ResponseEntity<Page<MaintenancePlanComputerResponseDTO>> findAllByComputerId(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam Long computerId
    ) {
        LOGGER.info("MaintenancePlanComputerController - findAllByComputerId - page: {}, size: {}, computerId: {}", page, size, computerId);
        return new ResponseEntity<>(maintenancePlanComputerService.findAllByComputerId(page, size, computerId), HttpStatus.OK);
    }

    @GetMapping(
            value = "/findById",
            params = {"maintenancePlanComputerId"}
    )
    public ResponseEntity<MaintenancePlanComputerResponseDTO> findById(
            @RequestParam Long maintenancePlanComputerId
    ) {
        LOGGER.info("MaintenancePlanComputerResponseDTO - findById - maintenancePlanComputerId: {}", maintenancePlanComputerId);
        return new ResponseEntity<>(maintenancePlanComputerService.findById(maintenancePlanComputerId), HttpStatus.OK);
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

    @PatchMapping(
            value = "/reprogram",
            params = {"id", "datePlanning"}
    )
    public ResponseEntity<MaintenancePlanComputerResponseDTO> reprogramMaintenancePlanComputer(@RequestParam Long id, @RequestParam LocalDateTime datePlanning) {
        LOGGER.info("MaintenancePlanComputerController - reprogramMaintenancePlanComputer - id: {}, datePlanning: {}", id, datePlanning);
        return new ResponseEntity<>(maintenancePlanComputerService.reprogram(id, datePlanning), HttpStatus.OK);
    }

    @PatchMapping(
            value = "/complete",
            params = {"id"}
    )
    public ResponseEntity<MaintenancePlanComputerResponseDTO> completedMaintenancePlanComputer(@RequestParam Long id) {
        LOGGER.info("MaintenancePlanComputerController - completedMaintenancePlanComputer - id: {}", id);
        return new ResponseEntity<>(maintenancePlanComputerService.completed(id), HttpStatus.OK);
    }

}