package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import co.com.assets_service.model.MaintenancePlan;
import co.com.assets_service.dto.MaintenancePlanCreateDTO;
import co.com.assets_service.dto.MaintenancePlanUpdateDTO;
import co.com.assets_service.dto.MaintenancePlanResponseDTO;

@Mapper(componentModel = "spring")
public interface MaintenancePlanMapper {
    MaintenancePlanResponseDTO entityToResponseDTO(MaintenancePlan maintenancePlan);
    MaintenancePlan createDTOToEntity(MaintenancePlanCreateDTO maintenancePlanCreateDTO);
    MaintenancePlan updateDTOToEntity(MaintenancePlanUpdateDTO maintenancePlanUpdateDTO);
}