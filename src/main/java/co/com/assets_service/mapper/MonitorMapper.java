package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import co.com.assets_service.model.Monitor;
import co.com.assets_service.dto.MonitorCreateDTO;
import co.com.assets_service.dto.MonitorUpdateDTO;
import co.com.assets_service.dto.MonitorResponseDTO;

@Mapper(
        componentModel = "spring",
        uses = {
                StateMapper.class,
                ManufacturerMapper.class,
                InternalCodeMapper.class
        }
)
public interface MonitorMapper {
    @Mapping(source = "state", target = "stateResponseDTO")
    @Mapping(source = "manufacturer", target = "manufacturerResponseDTO")
    @Mapping(source = "internalCode", target = "internalCodeResponseDTO")
    MonitorResponseDTO entityToResponseDTO(Monitor monitor);
    Monitor createDTOToEntity(MonitorCreateDTO monitorCreateDTO);
    Monitor updateDTOToEntity(MonitorUpdateDTO monitorUpdateDTO);
}