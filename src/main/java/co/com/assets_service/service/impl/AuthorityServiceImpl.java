package co.com.assets_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import co.com.assets_service.model.Authority;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import co.com.assets_service.mapper.AuthorityMapper;
import co.com.assets_service.dto.AuthorityResponseDTO;
import co.com.assets_service.service.AuthorityService;
import co.com.assets_service.exception.NoContentException;
import co.com.assets_service.repository.AuthorityRepository;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityMapper authorityMapper;
    private final AuthorityRepository authorityRepository;

    @Override
    public Page<AuthorityResponseDTO> getAllAuthorities(int page, int size) {
        Page<Authority> authorities = authorityRepository.findAll(
                PageRequest.of(page, size, Sort.by("id").ascending()));
        if (authorities.isEmpty())
            throw new NoContentException("Authority-Not-Content-204", HttpStatus.NOT_FOUND, "No authorities found");
        return authorities.map(authorityMapper::entityToResponseDTO);
    }
}
