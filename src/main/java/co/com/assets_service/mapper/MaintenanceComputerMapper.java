package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import co.com.assets_service.dto.*;
import co.com.assets_service.model.MaintenanceComputer;

@Mapper(
        componentModel = "spring",
        uses = {
                MaintenancePlanComputerMapper.class
        }
)
public interface MaintenanceComputerMapper {
    @Mapping(source = "maintenancePlanComputer", target = "maintenancePlanComputerResponseDTO")
    MaintenanceComputerResponseDTO entityToResponseDTO(MaintenanceComputer maintenanceComputer);
    MaintenanceComputer createDTOToEntity(MaintenanceComputerCreateDTO maintenanceComputerCreateDTO);
    MaintenanceComputer updateDTOToEntity(MaintenanceComputerUpdateDTO maintenanceComputerUpdateDTO);
}