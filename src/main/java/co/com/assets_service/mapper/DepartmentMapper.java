package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import co.com.assets_service.model.Department;
import co.com.assets_service.dto.DepartmentUpdateDTO;
import co.com.assets_service.dto.DepartmentCreateDTO;
import co.com.assets_service.dto.DepartmentResponseDTO;

@Mapper(
        componentModel = "spring",
        uses = {
                CostCenterMapper.class
        }
)
public interface DepartmentMapper {
    @Mapping(source = "costCenter", target = "costCenterResponseDTO")
    DepartmentResponseDTO entityToResponseDTO(Department department);
    Department createDTOToEntity(DepartmentCreateDTO departmentCreateDTO);
    Department updateDTOToEntity(DepartmentUpdateDTO departmentUpdateDTO);
}