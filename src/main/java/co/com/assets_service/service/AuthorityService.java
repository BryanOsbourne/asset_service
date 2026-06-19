package co.com.assets_service.service;

import org.springframework.data.domain.Page;
import co.com.assets_service.dto.AuthorityResponseDTO;

public interface AuthorityService {
    Page<AuthorityResponseDTO> getAllAuthorities(int page, int size);
}