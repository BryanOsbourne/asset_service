package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import co.com.assets_service.model.MaintenanceActivity;
import co.com.assets_service.dto.MaintenanceActivityUpdateDTO;
import co.com.assets_service.dto.MaintenanceActivityCreateDTO;
import co.com.assets_service.dto.MaintenanceActivityResponseDTO;

@Mapper(
        componentModel = "spring",
        uses = {
                MaintenanceComputerMapper.class
        }
)
public interface MaintenanceActivityMapper {
    @Mapping(source = "maintenanceComputer", target = "maintenanceComputerResponseDTO")
    MaintenanceActivityResponseDTO entityToResponseDTO(MaintenanceActivity maintenanceActivity);
    MaintenanceActivity createDTOToEntity(MaintenanceActivityCreateDTO maintenanceActivityCreateDTO);
    MaintenanceActivity updateDTOToEntity(MaintenanceActivityUpdateDTO maintenanceActivityUpdateDTO);
}