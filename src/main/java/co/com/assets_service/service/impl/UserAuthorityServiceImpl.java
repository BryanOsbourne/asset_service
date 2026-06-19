package co.com.assets_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import co.com.assets_service.model.UserAuthority;
import org.springframework.data.domain.PageRequest;
import co.com.assets_service.mapper.UserAuthorityMapper;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.dto.UserAuthorityResponseDTO;
import co.com.assets_service.service.UserAuthorityService;
import co.com.assets_service.exception.NoContentException;
import co.com.assets_service.repository.UserAuthorityRepository;

@Service
@RequiredArgsConstructor
public class UserAuthorityServiceImpl implements UserAuthorityService {

    private final UserAuthorityMapper userAuthorityMapper;
    private final UserAuthorityRepository userAuthorityRepository;

    @Override
    public Page<UserAuthorityResponseDTO> getUserAuthoritiesByUserId(int page, int size, Long userId) {
        Page<UserAuthority> userAuthorities = userAuthorityRepository.findAllByUserId(
                userId,
                PageRequest.of(page, size, Sort.by("id").ascending()));
        if (userAuthorities.isEmpty())
            throw new NoContentException("UserAuthority-Not-Content-204", HttpStatus.NOT_FOUND, "No UserAuthorities found");
        return userAuthorities.map(userAuthorityMapper::entityToResponseDTO);
    }

    @Override
    public boolean existsByUserIdAndAuthorityId(Long userId, Long authorityId) {
        if (userAuthorityRepository.existsByUserIdAndAuthorityId(userId, authorityId))
            throw new BusinessException("UserAuthority-Conflict-409", HttpStatus.CONFLICT, "The user already has this authority");
        return false;
    }
}