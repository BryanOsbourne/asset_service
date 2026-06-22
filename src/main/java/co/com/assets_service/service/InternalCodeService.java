package co.com.assets_service.service;

import org.springframework.data.domain.Page;
import co.com.assets_service.dto.InternalCodeResponseDTO;

public interface InternalCodeService {
    Page<InternalCodeResponseDTO> getAllInternalCodes(int page, int size);
}