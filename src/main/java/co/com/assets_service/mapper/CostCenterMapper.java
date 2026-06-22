package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import co.com.assets_service.model.CostCenter;
import co.com.assets_service.dto.CostCenterUpdateDTO;
import co.com.assets_service.dto.CostCenterCreateDTO;
import co.com.assets_service.dto.CostCenterResponseDTO;

@Mapper(componentModel = "spring")
public interface CostCenterMapper {
    CostCenter createDTOToEntity(CostCenterCreateDTO costCenterCreateDTO);
    CostCenterResponseDTO entityToResponseDTO(CostCenter costCenter);
    CostCenter updateDTOToEntity(CostCenterUpdateDTO costCenterUpdateDTO);
}