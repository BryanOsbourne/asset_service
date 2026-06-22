package co.com.assets_service.service;

import java.util.List;
import co.com.assets_service.dto.TypeComputerCreateDTO;
import co.com.assets_service.dto.TypeComputerUpdateDTO;
import co.com.assets_service.dto.TypeComputerResponseDTO;

public interface TypeComputerService {
    List<TypeComputerResponseDTO> getAllTypeComputers();
    TypeComputerResponseDTO createTypeComputer(TypeComputerCreateDTO typeComputerCreateDTO);
    TypeComputerResponseDTO updateTypeComputer(TypeComputerUpdateDTO typeComputerUpdateDTO);
}