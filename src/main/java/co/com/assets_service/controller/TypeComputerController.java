package co.com.assets_service.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.dto.TypeComputerUpdateDTO;
import co.com.assets_service.dto.TypeComputerCreateDTO;
import co.com.assets_service.dto.TypeComputerResponseDTO;
import co.com.assets_service.service.TypeComputerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/type-computer")
public class TypeComputerController {

    private final TypeComputerService typeComputerService;

    private final Logger LOGGER = LoggerFactory.getLogger(TypeComputerController.class);

    @GetMapping(
            value = "/findAll",
            produces = "application/json"
    )
    public ResponseEntity<List<TypeComputerResponseDTO>> findAll() {
        return new ResponseEntity<>(typeComputerService.getAllTypeComputers(), HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<TypeComputerResponseDTO> createTypeComputer(@Valid @RequestBody TypeComputerCreateDTO typeComputerCreateDTO) {
        LOGGER.info("TypeComputerController - createTypeComputer - typeComputerCreateDTO: {}", typeComputerCreateDTO);
        return new ResponseEntity<>(typeComputerService.createTypeComputer(typeComputerCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<TypeComputerResponseDTO> updateTypeComputer(@Valid @RequestBody TypeComputerUpdateDTO typeComputerUpdateDTO) {
        LOGGER.info("TypeComputerController - updateTypeComputer - typeComputerUpdateDTO: {}", typeComputerUpdateDTO);
        return new ResponseEntity<>(typeComputerService.updateTypeComputer(typeComputerUpdateDTO), HttpStatus.OK);
    }

}