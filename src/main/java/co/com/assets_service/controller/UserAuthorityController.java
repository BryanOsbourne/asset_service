package co.com.assets_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import co.com.assets_service.dto.UserAuthorityResponseDTO;
import co.com.assets_service.service.UserAuthorityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user-authorities")
public class UserAuthorityController {

    private final UserAuthorityService userAuthorityService;

    private final Logger LOGGER = LoggerFactory.getLogger(UserAuthorityController.class);

    @GetMapping(
            value = "/findAll",
            params = {"page", "size", "userId"}
    )
    public ResponseEntity<Page<UserAuthorityResponseDTO>> getAllAuthoritiesByUserId(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam Long userId
    ) {
        LOGGER.info("UserAuthorityController - getAllAuthoritiesByUserId - page: {}, size: {}, userId: {}", page, size, userId);
        return new ResponseEntity<>(userAuthorityService.getUserAuthoritiesByUserId(page, size, userId), HttpStatus.OK);
    }
}