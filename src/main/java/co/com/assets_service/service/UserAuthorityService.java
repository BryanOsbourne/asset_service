package co.com.assets_service.service;

import org.springframework.data.domain.Page;
import co.com.assets_service.dto.UserAuthorityResponseDTO;

public interface UserAuthorityService {
    boolean existsByUserIdAndAuthorityId(Long userId, Long authorityId);
    Page<UserAuthorityResponseDTO> getUserAuthoritiesByUserId(int page, int size, Long userId);
}