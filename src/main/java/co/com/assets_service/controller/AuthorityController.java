package co.com.assets_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import co.com.assets_service.dto.AuthorityResponseDTO;
import co.com.assets_service.service.AuthorityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;

    @GetMapping(
            value = "/findAll",
            params = {"page", "size"}
    )
    public ResponseEntity<Page<AuthorityResponseDTO>> getAllAuthorities(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return new ResponseEntity<>(authorityService.getAllAuthorities(page, size), HttpStatus.OK);
    }
}