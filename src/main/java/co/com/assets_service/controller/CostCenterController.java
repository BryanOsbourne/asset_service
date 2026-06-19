package co.com.assets_service.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.dto.CostCenterUpdateDTO;
import co.com.assets_service.dto.CostCenterCreateDTO;
import co.com.assets_service.dto.CostCenterResponseDTO;
import co.com.assets_service.service.CostCenterService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/costCenter")
public class CostCenterController {

    private final CostCenterService CostCenterService;

    private final Logger LOGGER = LoggerFactory.getLogger(CostCenterController.class);

    @GetMapping(
            value = "/findAll",
            produces = "application/json"
    )
    public ResponseEntity<List<CostCenterResponseDTO>> findAll() {
        return new ResponseEntity<>(CostCenterService.getAllCostCenters(), HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<CostCenterResponseDTO> createCostCenter(@Valid @RequestBody CostCenterCreateDTO CostCenterCreateDTO) {
        LOGGER.info("CostCenterController - createCostCenter - CostCenterCreateDTO: {}", CostCenterCreateDTO);
        return new ResponseEntity<>(CostCenterService.createCostCenter(CostCenterCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<CostCenterResponseDTO> updateCostCenter(@Valid @RequestBody CostCenterUpdateDTO CostCenterUpdateDTO) {
        LOGGER.info("CostCenterController - updateCostCenter - CostCenterUpdateDTO: {}", CostCenterUpdateDTO);
        return new ResponseEntity<>(CostCenterService.updateCostCenter(CostCenterUpdateDTO), HttpStatus.OK);
    }

}