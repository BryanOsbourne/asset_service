package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import co.com.assets_service.model.Department;
import co.com.assets_service.dto.DepartmentCreateDTO;
import co.com.assets_service.dto.DepartmentResponseDTO;

@Mapper(
        componentModel = "spring",
        uses = {
                CostCenterMapper.class
        }
)
public interface DepartmentMapper {
    Department createDTOToEntity(DepartmentCreateDTO DepartmentCreateDTO);
    @Mapping(source = "costCenter", target = "costCenterResponseDTO")
    DepartmentResponseDTO entityToBasicResponseDTO(Department Department);
}