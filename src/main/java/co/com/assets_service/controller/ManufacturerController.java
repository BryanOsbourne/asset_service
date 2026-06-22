package co.com.assets_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.dto.ManufacturerUpdateDTO;
import co.com.assets_service.dto.ManufacturerCreateDTO;
import co.com.assets_service.dto.ManufacturerResponseDTO;
import co.com.assets_service.service.ManufacturerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    private final Logger LOGGER = LoggerFactory.getLogger(ManufacturerController.class);

    @GetMapping(
            value = "/findAll",
            params = {"page", "size"},
            produces = "application/json"
    )
    public ResponseEntity<Page<ManufacturerResponseDTO>> findAll(
            @RequestParam int page,
            @RequestParam int size
    ) {
        LOGGER.info("ManufacturerController - findAll - page: {}, size: {}", page, size);
        return new ResponseEntity<>(manufacturerService.getAllManufacturers(page, size), HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<ManufacturerResponseDTO> createManufacturer(@Valid @RequestBody ManufacturerCreateDTO manufacturerCreateDTO) {
        LOGGER.info("ManufacturerController - createManufacturer - ManufacturerCreateDTO: {}", manufacturerCreateDTO);
        return new ResponseEntity<>(manufacturerService.createManufacturer(manufacturerCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<ManufacturerResponseDTO> updateManufacturer(@Valid @RequestBody ManufacturerUpdateDTO manufacturerUpdateDTO) {
        LOGGER.info("ManufacturerController - updateManufacturer - ManufacturerUpdateDTO: {}", manufacturerUpdateDTO);
        return new ResponseEntity<>(manufacturerService.updateManufacturer(manufacturerUpdateDTO), HttpStatus.OK);
    }

}