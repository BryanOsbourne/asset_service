package co.com.assets_service.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import co.com.assets_service.model.CostCenter;
import org.springframework.stereotype.Service;
import co.com.assets_service.model.Department;
import co.com.assets_service.dto.DepartmentUpdateDTO;
import co.com.assets_service.mapper.CostCenterMapper;
import co.com.assets_service.dto.DepartmentCreateDTO;
import co.com.assets_service.mapper.DepartmentMapper;
import co.com.assets_service.dto.DepartmentResponseDTO;
import co.com.assets_service.service.DepartmentService;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.exception.NoContentException;
import co.com.assets_service.repository.CostCenterRepository;
import co.com.assets_service.repository.DepartmentRepository;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final CostCenterMapper costCenterMapper;
    private final DepartmentRepository departmentRepository;
    private final CostCenterRepository costCenterRepository;

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        List<Department> DepartmentList = departmentRepository.findAll();
        if (DepartmentList.isEmpty())
            throw new NoContentException("Department-Not-Content-204", HttpStatus.NOT_FOUND, "No Departments found");
        return DepartmentList.stream().map(departmentMapper::entityToBasicResponseDTO).toList();
    }

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentCreateDTO departmentCreateDTO) {
        String name = departmentCreateDTO.getName().trim().toUpperCase();
        if (departmentRepository.existsByName(name)) {
            throw new BusinessException(
                    "Department-Conflict-409",
                    HttpStatus.CONFLICT,
                    "Department name already exists"
            );
        }

        CostCenter costCenter = costCenterRepository.findById(
                departmentCreateDTO.getCostCenter().getId()).orElseThrow(() -> new NoContentException(
                "CostCenter-Not-Found-404",
                HttpStatus.NOT_FOUND,
                "Cost center not found"
        ));

        departmentCreateDTO.setName(name);
        departmentCreateDTO.setCostCenter(costCenterMapper.entityToResponseDTO(costCenter));

        Department department = departmentRepository.save(
                departmentMapper.createDTOToEntity(departmentCreateDTO)
        );

        return departmentMapper.entityToBasicResponseDTO(department);
    }

    @Override
    public DepartmentResponseDTO updateDepartment(DepartmentUpdateDTO departmentUpdateDTO) {
        Department Department = departmentRepository.findById(departmentUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "Department-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Department not found"
                ));

        String name = departmentUpdateDTO.getName().trim().toUpperCase();
        departmentRepository.findByName(name)
                .filter(existing -> !existing.getId().equals(Department.getId()))
                .ifPresent(existing -> {
                    throw new BusinessException(
                            "Department-Conflict-409",
                            HttpStatus.CONFLICT,
                            "Department name already exists"
                    );
                });

        Department.setName(name);
        Department updatedDepartment = departmentRepository.save(Department);
        return departmentMapper.entityToBasicResponseDTO(updatedDepartment);
    }
}