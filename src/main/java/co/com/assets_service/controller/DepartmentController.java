package co.com.assets_service.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.dto.DepartmentCreateDTO;
import co.com.assets_service.dto.DepartmentUpdateDTO;
import co.com.assets_service.dto.DepartmentResponseDTO;
import co.com.assets_service.service.DepartmentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/department")
public class DepartmentController {

    private final DepartmentService DepartmentService;

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @GetMapping(
            value = "/findAll",
            produces = "application/json"
    )
    public ResponseEntity<List<DepartmentResponseDTO>> findAll() {
        return new ResponseEntity<>(DepartmentService.getAllDepartments(), HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<DepartmentResponseDTO> createDepartment(@Valid @RequestBody DepartmentCreateDTO DepartmentCreateDTO) {
        LOGGER.info("DepartmentController - createDepartment - DepartmentCreateDTO: {}", DepartmentCreateDTO);
        return new ResponseEntity<>(DepartmentService.createDepartment(DepartmentCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(@Valid @RequestBody DepartmentUpdateDTO DepartmentUpdateDTO) {
        LOGGER.info("DepartmentController - updateDepartment - DepartmentUpdateDTO: {}", DepartmentUpdateDTO);
        return new ResponseEntity<>(DepartmentService.updateDepartment(DepartmentUpdateDTO), HttpStatus.OK);
    }

}