package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import co.com.assets_service.model.User;
import co.com.assets_service.dto.UserBasicResponseDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserBasicResponseDTO entityToBasicResponseDTO(User user);
}