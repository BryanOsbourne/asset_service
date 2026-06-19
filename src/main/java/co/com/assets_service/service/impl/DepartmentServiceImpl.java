package co.com.assets_service.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import co.com.assets_service.model.Department;
import co.com.assets_service.dto.DepartmentUpdateDTO;
import co.com.assets_service.dto.DepartmentCreateDTO;
import co.com.assets_service.mapper.DepartmentMapper;
import co.com.assets_service.dto.DepartmentResponseDTO;
import co.com.assets_service.service.DepartmentService;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.exception.NoContentException;
import co.com.assets_service.repository.DepartmentRepository;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper DepartmentMapper;
    private final DepartmentRepository DepartmentRepository;

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        List<Department> DepartmentList = DepartmentRepository.findAll();
        if (DepartmentList.isEmpty())
            throw new NoContentException("Department-Not-Content-204", HttpStatus.NOT_FOUND, "No Departments found");
        return DepartmentList.stream().map(DepartmentMapper::entityToBasicResponseDTO).toList();
    }

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentCreateDTO DepartmentCreateDTO) {
        String name = DepartmentCreateDTO.getName().trim().toUpperCase();
        if (DepartmentRepository.existsByName(name)) {
            throw new BusinessException(
                    "Department-Conflict-409",
                    HttpStatus.CONFLICT,
                    "Department name already exists"
            );
        }
        DepartmentCreateDTO.setName(name);
        Department Department = DepartmentRepository.save(
                DepartmentMapper.createDTOToEntity(DepartmentCreateDTO)
        );

        return DepartmentMapper.entityToBasicResponseDTO(Department);
    }

    @Override
    public DepartmentResponseDTO updateDepartment(DepartmentUpdateDTO DepartmentUpdateDTO) {
        Department Department = DepartmentRepository.findById(DepartmentUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "Department-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Department not found"
                ));

        String name = DepartmentUpdateDTO.getName().trim().toUpperCase();
        DepartmentRepository.findByName(name)
                .filter(existing -> !existing.getId().equals(Department.getId()))
                .ifPresent(existing -> {
                    throw new BusinessException(
                            "Department-Conflict-409",
                            HttpStatus.CONFLICT,
                            "Department name already exists"
                    );
                });

        Department.setName(name);
        Department updatedDepartment = DepartmentRepository.save(Department);
        return DepartmentMapper.entityToBasicResponseDTO(updatedDepartment);
    }
}