package co.com.assets_service.service;

import org.springframework.data.domain.Page;
import co.com.assets_service.dto.ComputerCreateDTO;
import co.com.assets_service.dto.ComputerUpdateDTO;
import co.com.assets_service.dto.ComputerResponseDTO;

public interface ComputerService {
    ComputerResponseDTO findById(Long id);
    Page<ComputerResponseDTO> findAll(int page, int size);
    ComputerResponseDTO assignEmployee(Long id, Long employeeId);
    ComputerResponseDTO createComputer(ComputerCreateDTO computerCreateDTO);
    ComputerResponseDTO updateComputer(ComputerUpdateDTO computerUpdateDTO);
}