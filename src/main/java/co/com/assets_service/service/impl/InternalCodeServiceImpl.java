package co.com.assets_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import co.com.assets_service.model.InternalCode;
import org.springframework.data.domain.PageRequest;
import co.com.assets_service.mapper.InternalCodeMapper;
import co.com.assets_service.service.InternalCodeService;
import co.com.assets_service.dto.InternalCodeResponseDTO;
import co.com.assets_service.exception.NoContentException;
import co.com.assets_service.repository.InternalCodeRepository;

@Service
@RequiredArgsConstructor
public class InternalCodeServiceImpl implements InternalCodeService {

    private final InternalCodeMapper internalCodeMapper;
    private final InternalCodeRepository internalCodeRepository;

    @Override
    public Page<InternalCodeResponseDTO> getAllInternalCodes(int page, int size) {
        Page<InternalCode> internalCodes = internalCodeRepository.findAll(
                PageRequest.of(page, size, Sort.by("id").ascending()));
        if (internalCodes.isEmpty())
            throw new NoContentException("InternalCodes-Not-Content-204", HttpStatus.NOT_FOUND, "No InternalCodes found");
        return internalCodes.map(internalCodeMapper::entityToResponseDTO);
    }

}