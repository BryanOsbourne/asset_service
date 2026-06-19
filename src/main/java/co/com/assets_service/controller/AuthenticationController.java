package co.com.assets_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import co.com.assets_service.service.AuthenticationService;
import co.com.assets_service.security.AuthenticationRequest;
import co.com.assets_service.security.AuthenticationResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping(
            value = "/login"
    )
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        LOGGER.info("AuthenticationController - login - request: {}", request);
        return new ResponseEntity<>(authenticationService.login(request), HttpStatus.OK);
    }
}