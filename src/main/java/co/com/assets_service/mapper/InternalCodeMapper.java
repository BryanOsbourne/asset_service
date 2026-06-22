package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import co.com.assets_service.model.InternalCode;
import co.com.assets_service.dto.InternalCodeCreateDTO;
import co.com.assets_service.dto.InternalCodeUpdateDTO;
import co.com.assets_service.dto.InternalCodeResponseDTO;

@Mapper(componentModel = "spring")
public interface InternalCodeMapper {
    InternalCode createDTOToEntity(InternalCodeCreateDTO internalCodeCreateDTO);
    InternalCodeResponseDTO entityToResponseDTO(InternalCode internalCode);
    InternalCode updateDTOToEntity(InternalCodeUpdateDTO internalCodeUpdateDTO);
}