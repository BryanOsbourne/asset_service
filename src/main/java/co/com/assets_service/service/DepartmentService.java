package co.com.assets_service.service;

import java.util.List;
import co.com.assets_service.dto.DepartmentCreateDTO;
import co.com.assets_service.dto.DepartmentUpdateDTO;
import co.com.assets_service.dto.DepartmentResponseDTO;

public interface DepartmentService {
    List<DepartmentResponseDTO> getAllDepartments();
    DepartmentResponseDTO createDepartment(DepartmentCreateDTO DepartmentCreateDTO);
    DepartmentResponseDTO updateDepartment(DepartmentUpdateDTO DepartmentUpdateDTO);
}