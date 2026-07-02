package co.com.assets_service.service.impl;

import co.com.assets_service.model.*;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import co.com.assets_service.utils.Utils;
import co.com.assets_service.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import co.com.assets_service.mapper.ComputerMapper;
import co.com.assets_service.dto.ComputerCreateDTO;
import co.com.assets_service.dto.ComputerUpdateDTO;
import co.com.assets_service.dto.ComputerResponseDTO;
import co.com.assets_service.service.ComputerService;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.exception.NoContentException;

@Service
@RequiredArgsConstructor
public class ComputerServiceImpl implements ComputerService {

    private final ComputerMapper computerMapper;
    private final StateRepository stateRepository;
    private final ComputerRepository computerRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final InternalCodeRepository internalCodeRepository;
    private final TypeComputerRepository typeComputerRepository;
    private final ManufacturerRepository manufacturerRepository;

    @Override
    public ComputerResponseDTO findById(Long id) {
        Computer computer = computerRepository.findById(id).orElseThrow(
                () -> new NoContentException(
                        "Computer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Computer not found")
        );
        return computerMapper.entityToResponseDTO(computer);
    }

    @Override
    public Page<ComputerResponseDTO> findAll(int page, int size) {
        Page<Computer> computers = computerRepository.findAll(
                PageRequest.of(page, size, Sort.by("id").ascending()));
        if (computers.isEmpty())
            throw new NoContentException("Computers-Not-Content-204", HttpStatus.NOT_FOUND, "No Computers found");
        return computers.map(computerMapper::entityToResponseDTO);
    }

    @Override
    public ComputerResponseDTO relocate(Long id, Long departmentId) {
        Computer computer = computerRepository.findById(id)
                .orElseThrow(() -> new NoContentException(
                        "Computer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Computer not found"
                ));

        if (!computer.getIsEnabled())
            throw new BusinessException("Computer-Not-Enabled-400", HttpStatus.BAD_REQUEST, "Computer not enabled");

        computer.setDepartment(getDepartment(departmentId));

        return computerMapper.entityToResponseDTO(
                computerRepository.save(computer)
        );
    }

    @Override
    @Transactional
    public ComputerResponseDTO createComputer(ComputerCreateDTO computerCreateDTO) {
        computerCreateDTO.setName(Utils.normalizeName(computerCreateDTO.getName()));
        InternalCode internalCode = new InternalCode();
        internalCode.setPrefix("SF-");
        internalCode.setSeries("000-");
        Computer computer = buildComputerForCreate(computerCreateDTO);
        computer.setInternalCode(internalCodeRepository.save(internalCode));
        return computerMapper.entityToResponseDTO(
                computerRepository.save(computer)
        );
    }

    @Override
    @Transactional
    public ComputerResponseDTO updateComputer(ComputerUpdateDTO computerUpdateDTO) {
        computerUpdateDTO.setName(Utils.normalizeName(computerUpdateDTO.getName()));
        Computer computer = buildComputerForUpdate(computerUpdateDTO);
        return computerMapper.entityToResponseDTO(
                computerRepository.save(computer)
        );
    }

    @Override
    @Transactional
    public ComputerResponseDTO assignEmployee(Long id, Long employeeId) {
        Computer computer = computerRepository.findById(id)
                .orElseThrow(() -> new NoContentException(
                        "Computer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Computer not found"
                ));

        if (!computer.getIsEnabled())
            throw new BusinessException("Computer-Not-Enabled-400", HttpStatus.BAD_REQUEST, "Computer not enabled");

        computer.setEmployee(getEmployee(employeeId));

        return computerMapper.entityToResponseDTO(
                computerRepository.save(computer)
        );
    }

    private Computer buildComputerForCreate(ComputerCreateDTO computerCreateDTO) {
        validateUniqueName(computerCreateDTO.getName(), null);
        Computer computer = computerMapper.createDTOToEntity(computerCreateDTO);
        computer.setState(getState(computerCreateDTO.getStateId()));
        computer.setEmployee(getEmployee(computerCreateDTO.getEmployeeId()));
        computer.setDepartment(getDepartment(computerCreateDTO.getDepartmentId()));
        computer.setManufacturer(getManufacturer(computerCreateDTO.getManufacturerId()));
        computer.setTypeComputer(getTypeComputer(computerCreateDTO.getTypeComputerId()));
        return computer;
    }

    private Computer buildComputerForUpdate(ComputerUpdateDTO computerUpdateDTO) {
        Computer computer = computerRepository.findById(computerUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "Computer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Computer not found"
                ));
        validateUniqueName(computerUpdateDTO.getName(), computerUpdateDTO.getId());
        computer.setName(computerUpdateDTO.getName());
        computer.setModel(computerUpdateDTO.getModel());
        computer.setIsEnabled(computerUpdateDTO.getIsEnabled());
        computer.setState(getState(computerUpdateDTO.getStateId()));
        computer.setSerialNumber(computerUpdateDTO.getSerialNumber());
        computer.setManufacturer(getManufacturer(computerUpdateDTO.getManufacturerId()));
        computer.setTypeComputer(getTypeComputer(computerUpdateDTO.getTypeComputerId()));
        return computer;
    }

    private void validateUniqueName(String name, Long currentId) {
        computerRepository.findByName(name)
                .filter(c -> currentId == null || !c.getId().equals(currentId))
                .ifPresent(c -> {
                    throw new BusinessException(
                            "Computer-Conflict-409",
                            HttpStatus.CONFLICT,
                            "Computer name already exists"
                    );
                });
    }

    private Manufacturer getManufacturer(Long id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new NoContentException(
                        "Manufacturer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Manufacturer not found"
                ));
    }

    private TypeComputer getTypeComputer(Long id) {
        return typeComputerRepository.findById(id)
                .orElseThrow(() -> new NoContentException(
                        "TypeComputer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Type computer not found"
                ));
    }

    private State getState(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new NoContentException(
                        "State-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "State not found"
                ));
    }

    private Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoContentException(
                        "Employee-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Employee not found"
                ));
    }

    private Department getDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NoContentException(
                        "Department-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Department not found"
                ));
    }

}