package co.com.assets_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.assets_service.dto.InternalCodeResponseDTO;
import co.com.assets_service.service.InternalCodeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/internalCode")
public class InternalCodeController {

    private final InternalCodeService internalCodeService;

    private final Logger LOGGER = LoggerFactory.getLogger(InternalCodeController.class);

    @GetMapping(
            value = "/findAll",
            params = {"page", "size"},
            produces = "application/json"
    )
    public ResponseEntity<Page<InternalCodeResponseDTO>> findAll(
            @RequestParam int page,
            @RequestParam int size
    ) {
        LOGGER.info("InternalCodeController - findAll - page: {}, size: {}", page, size);
        return new ResponseEntity<>(internalCodeService.getAllInternalCodes(page, size), HttpStatus.OK);
    }

}