package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import co.com.assets_service.model.Manufacturer;
import co.com.assets_service.dto.ManufacturerUpdateDTO;
import co.com.assets_service.dto.ManufacturerCreateDTO;
import co.com.assets_service.dto.ManufacturerResponseDTO;

@Mapper(componentModel = "spring")
public interface ManufacturerMapper {
    Manufacturer createDTOToEntity(ManufacturerCreateDTO manufacturerCreateDTO);
    ManufacturerResponseDTO entityToResponseDTO(Manufacturer manufacturer);
    Manufacturer updateDTOToEntity(ManufacturerUpdateDTO manufacturerUpdateDTO);
}