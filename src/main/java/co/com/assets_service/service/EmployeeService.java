package co.com.assets_service.service;

import co.com.assets_service.dto.*;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    Page<EmployeeResponseDTO> findAll(int page, int size);
    EmployeeResponseDTO createEmployee(EmployeeCreateDTO employeeCreateDTO);
    EmployeeResponseDTO updateEmployee(EmployeeUpdateDTO employeeUpdateDTO);
}