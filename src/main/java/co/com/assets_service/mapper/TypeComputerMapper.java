package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import co.com.assets_service.model.TypeComputer;
import co.com.assets_service.dto.TypeComputerCreateDTO;
import co.com.assets_service.dto.TypeComputerResponseDTO;

@Mapper(componentModel = "spring")
public interface TypeComputerMapper {
    TypeComputerResponseDTO entityToResponseDTO(TypeComputer typeComputer);
    TypeComputer createDTOToEntity(TypeComputerCreateDTO typeComputerCreateDTO);
}