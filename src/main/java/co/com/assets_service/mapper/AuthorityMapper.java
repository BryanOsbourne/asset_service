package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import co.com.assets_service.model.Authority;
import co.com.assets_service.dto.AuthorityResponseDTO;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {
    AuthorityResponseDTO entityToResponseDTO(Authority authority);
}