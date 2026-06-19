package co.com.assets_service.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import co.com.assets_service.dto.StateCreateDTO;
import co.com.assets_service.dto.StateUpdateDTO;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.service.StateService;
import co.com.assets_service.dto.StateBasicResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/state")
public class StateController {

    private final StateService stateService;

    private final Logger LOGGER = LoggerFactory.getLogger(StateController.class);

    @GetMapping(
            value = "/findAll",
            produces = "application/json"
    )
    public ResponseEntity<List<StateBasicResponseDTO>> findAll() {
        return new ResponseEntity<>(stateService.getAllStates(), HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<StateBasicResponseDTO> createState(@Valid @RequestBody StateCreateDTO stateCreateDTO) {
        LOGGER.info("StateController - createState - stateCreateDTO: {}", stateCreateDTO);
        return new ResponseEntity<>(stateService.createState(stateCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<StateBasicResponseDTO> updateState(@Valid @RequestBody StateUpdateDTO stateUpdateDTO) {
        LOGGER.info("StateController - updateState - stateUpdateDTO: {}", stateUpdateDTO);
        return new ResponseEntity<>(stateService.updateState(stateUpdateDTO), HttpStatus.OK);
    }

}