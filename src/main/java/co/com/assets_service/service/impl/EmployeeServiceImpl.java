package co.com.assets_service.service.impl;

import lombok.RequiredArgsConstructor;
import co.com.assets_service.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import co.com.assets_service.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import co.com.assets_service.dto.EmployeeCreateDTO;
import co.com.assets_service.dto.EmployeeUpdateDTO;
import co.com.assets_service.mapper.EmployeeMapper;
import org.springframework.data.domain.PageRequest;
import co.com.assets_service.service.EmployeeService;
import co.com.assets_service.dto.EmployeeResponseDTO;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.exception.NoContentException;
import co.com.assets_service.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public Page<EmployeeResponseDTO> findAll(int page, int size) {
        Page<Employee> employees = employeeRepository.findAll(
                PageRequest.of(page, size, Sort.by("id").ascending()));
        if (employees.isEmpty())
            throw new NoContentException("Employer-Not-Content-204", HttpStatus.NOT_FOUND, "No Employer found");
        return employees.map(employeeMapper::entityToResponseDTO);
    }

    @Override
    @Transactional
    public EmployeeResponseDTO createEmployee(EmployeeCreateDTO employeeCreateDTO) {
        employeeCreateDTO.setName(Utils.normalizeName(employeeCreateDTO.getName()));
        employeeCreateDTO.setLastName(Utils.normalizeName(employeeCreateDTO.getLastName()));
        employeeCreateDTO.setSecondLastName(Utils.normalizeName(employeeCreateDTO.getSecondLastName()));
        validateUniqueName(
                employeeCreateDTO.getName(),
                employeeCreateDTO.getLastName(),
                employeeCreateDTO.getSecondLastName(),
                null);
        return employeeMapper.entityToResponseDTO(
                employeeRepository.save(
                        employeeMapper.createDTOToEntity(employeeCreateDTO)
                )
        );
    }

    @Override
    @Transactional
    public EmployeeResponseDTO updateEmployee(EmployeeUpdateDTO employeeUpdateDTO) {
        employeeUpdateDTO.setName(Utils.normalizeName(employeeUpdateDTO.getName()));
        employeeUpdateDTO.setLastName(Utils.normalizeName(employeeUpdateDTO.getLastName()));
        employeeUpdateDTO.setSecondLastName(Utils.normalizeName(employeeUpdateDTO.getSecondLastName()));
        employeeRepository.findById(employeeUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "Employee-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Employee not found"
                ));
        validateUniqueName(
                employeeUpdateDTO.getName(),
                employeeUpdateDTO.getLastName(),
                employeeUpdateDTO.getSecondLastName(),
                employeeUpdateDTO.getId());
        return employeeMapper.entityToResponseDTO(
                employeeRepository.save(
                        employeeMapper.updateDTOToEntity(employeeUpdateDTO)
                )
        );
    }

    private void validateUniqueName(String name, String lastName, String secondLastName, Long currentId) {
        employeeRepository.findByNameAndLastNameAndSecondLastName(name, lastName, secondLastName)
                .filter(c -> currentId == null || !c.getId().equals(currentId))
                .ifPresent(c -> {
                    throw new BusinessException(
                            "Computer-Conflict-409",
                            HttpStatus.CONFLICT,
                            "Computer name already exists"
                    );
                });
    }
}