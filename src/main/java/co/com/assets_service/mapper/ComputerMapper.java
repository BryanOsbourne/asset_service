package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import co.com.assets_service.model.Computer;
import co.com.assets_service.dto.ComputerUpdateDTO;
import co.com.assets_service.dto.ComputerCreateDTO;
import co.com.assets_service.dto.ComputerResponseDTO;

@Mapper(
        componentModel = "spring",
        uses = {
                StateMapper.class,
                TypeComputerMapper.class,
                ManufacturerMapper.class,
                InternalCodeMapper.class,
                EmployeeMapper.class,
                DepartmentMapper.class
        }
)
public interface ComputerMapper {
    @Mapping(source = "state", target = "stateResponseDTO")
    @Mapping(source = "typeComputer", target = "typeComputerResponseDTO")
    @Mapping(source = "manufacturer", target = "manufacturerResponseDTO")
    @Mapping(source = "internalCode", target = "internalCodeResponseDTO")
    @Mapping(source = "employee", target = "employeeResponseDTO")
    @Mapping(source = "department", target = "departmentResponseDTO")
    ComputerResponseDTO entityToResponseDTO(Computer computer);
    Computer createDTOToEntity(ComputerCreateDTO computerCreateDTO);
    Computer updateDTOToEntity(ComputerUpdateDTO computerUpdateDTO);
}