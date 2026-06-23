package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import co.com.assets_service.dto.*;
import co.com.assets_service.model.MaintenancePlanComputer;

@Mapper(
        componentModel = "spring",
        uses = {
                ComputerMapper.class,
                MaintenancePlanMapper.class
        }
)
public interface MaintenancePlanComputerMapper {
    @Mapping(source = "computer", target = "computerResponseDTO")
    @Mapping(source = "maintenancePlan", target = "maintenancePlanResponseDTO")
    MaintenancePlanComputerResponseDTO entityToResponseDTO(MaintenancePlanComputer maintenancePlanComputer);
    MaintenancePlanComputer createDTOToEntity(MaintenancePlanComputerCreateDTO maintenancePlanComputerCreateDTO);
    MaintenancePlanComputer updateDTOToEntity(MaintenancePlanComputerUpdateDTO maintenancePlanComputerUpdateDTO);
}