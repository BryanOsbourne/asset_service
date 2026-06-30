package co.com.assets_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.dto.ComputerUpdateDTO;
import co.com.assets_service.dto.ComputerCreateDTO;
import co.com.assets_service.dto.ComputerResponseDTO;
import co.com.assets_service.service.ComputerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/computer")
public class ComputerController {

    private final ComputerService computerService;

    private final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

    @GetMapping(
            value = "/findAll",
            params = {"page", "size"}
    )
    public ResponseEntity<Page<ComputerResponseDTO>> findAll(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return new ResponseEntity<>(computerService.findAll(page, size), HttpStatus.OK);
    }

    @GetMapping(
            value = "/findById",
            params = {"id"}
    )
    public ResponseEntity<ComputerResponseDTO> findById(@RequestParam Long id) {
        return new ResponseEntity<>(computerService.findById(id), HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<ComputerResponseDTO> createComputer(@Valid @RequestBody ComputerCreateDTO computerCreateDTO) {
        LOGGER.info("ComputerController - createComputer - computerCreateDTO: {}", computerCreateDTO);
        return new ResponseEntity<>(computerService.createComputer(computerCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<ComputerResponseDTO> updateComputer(@Valid @RequestBody ComputerUpdateDTO computerUpdateDTO) {
        LOGGER.info("ComputerController - updateComputer - computerUpdateDTO: {}", computerUpdateDTO);
        return new ResponseEntity<>(computerService.updateComputer(computerUpdateDTO), HttpStatus.OK);
    }

    @PatchMapping(
            value = "/assign",
            params = {"id","employeeId"}
    )
    public ResponseEntity<ComputerResponseDTO> assignEmployee(@RequestParam Long id, @RequestParam Long employeeId) {
        LOGGER.info("ComputerController - assignEmployee - id: {}, employeeId: {}", id, employeeId);
        return new ResponseEntity<>(computerService.assignEmployee(id, employeeId), HttpStatus.OK);
    }

}