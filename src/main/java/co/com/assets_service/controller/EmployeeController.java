package co.com.assets_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.dto.EmployeeCreateDTO;
import co.com.assets_service.dto.EmployeeUpdateDTO;
import co.com.assets_service.dto.EmployeeResponseDTO;
import co.com.assets_service.service.EmployeeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping(
            value = "/findAll",
            params = {"page", "size"}
    )
    public ResponseEntity<Page<EmployeeResponseDTO>> findAll(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return new ResponseEntity<>(employeeService.findAll(page, size), HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeCreateDTO employeeCreateDTO) {
        LOGGER.info("EmployeeController - createEmployee - employeeCreateDTO: {}", employeeCreateDTO);
        return new ResponseEntity<>(employeeService.createEmployee(employeeCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@Valid @RequestBody EmployeeUpdateDTO employeeUpdateDTO) {
        LOGGER.info("EmployeeController - updateEmployee - employeeUpdateDTO: {}", employeeUpdateDTO);
        return new ResponseEntity<>(employeeService.updateEmployee(employeeUpdateDTO), HttpStatus.OK);
    }

}