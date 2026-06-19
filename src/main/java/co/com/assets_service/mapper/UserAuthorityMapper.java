package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import co.com.assets_service.model.UserAuthority;
import co.com.assets_service.dto.UserAuthorityResponseDTO;

@Mapper(
        componentModel = "spring",
        uses = {
                AuthorityMapper.class
        }
)
public interface UserAuthorityMapper {
    @Mapping(source = "authority", target = "authorityResponseDTO")
    UserAuthorityResponseDTO entityToResponseDTO(UserAuthority entity);
}