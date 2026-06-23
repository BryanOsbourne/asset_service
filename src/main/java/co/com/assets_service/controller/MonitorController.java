package co.com.assets_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.dto.MonitorCreateDTO;
import co.com.assets_service.dto.MonitorUpdateDTO;
import co.com.assets_service.dto.MonitorResponseDTO;
import co.com.assets_service.service.MonitorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/monitor")
public class MonitorController {

    private final MonitorService monitorService;

    private final Logger LOGGER = LoggerFactory.getLogger(MonitorController.class);

    @GetMapping(
            value = "/findAll",
            params = {"page", "size"}
    )
    public ResponseEntity<Page<MonitorResponseDTO>> findAll(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return new ResponseEntity<>(monitorService.findAll(page, size), HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<MonitorResponseDTO> createMonitor(@Valid @RequestBody MonitorCreateDTO monitorCreateDTO) {
        LOGGER.info("MonitorController - createMonitor - MonitorCreateDTO: {}", monitorCreateDTO);
        return new ResponseEntity<>(monitorService.createMonitor(monitorCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<MonitorResponseDTO> updateMonitor(@Valid @RequestBody MonitorUpdateDTO monitorUpdateDTO) {
        LOGGER.info("MonitorController - updateMonitor - MonitorUpdateDTO: {}", monitorUpdateDTO);
        return new ResponseEntity<>(monitorService.updateMonitor(monitorUpdateDTO), HttpStatus.OK);
    }

}