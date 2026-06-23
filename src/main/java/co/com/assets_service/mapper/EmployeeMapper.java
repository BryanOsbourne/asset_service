package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import co.com.assets_service.model.Employee;
import co.com.assets_service.dto.EmployeeCreateDTO;
import co.com.assets_service.dto.EmployeeUpdateDTO;
import co.com.assets_service.dto.EmployeeResponseDTO;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeResponseDTO entityToResponseDTO(Employee employee);
    Employee createDTOToEntity(EmployeeCreateDTO employeeCreateDTO);
    Employee updateDTOToEntity(EmployeeUpdateDTO employeeUpdateDTO);
}