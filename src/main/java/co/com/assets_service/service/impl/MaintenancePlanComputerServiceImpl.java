package co.com.assets_service.service.impl;

import co.com.assets_service.model.*;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import co.com.assets_service.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.exception.NoContentException;
import co.com.assets_service.enums.MaintenancePlanningState;
import co.com.assets_service.mapper.MaintenancePlanComputerMapper;
import co.com.assets_service.dto.MaintenancePlanComputerCreateDTO;
import co.com.assets_service.dto.MaintenancePlanComputerUpdateDTO;
import co.com.assets_service.dto.MaintenancePlanComputerResponseDTO;
import co.com.assets_service.service.MaintenancePlanComputerService;

@Service
@RequiredArgsConstructor
public class MaintenancePlanComputerServiceImpl implements MaintenancePlanComputerService {

    private final ComputerRepository computerRepository;
    private final MaintenancePlanRepository maintenancePlanRepository;
    private final MaintenancePlanComputerMapper maintenancePlanComputerMapper;
    private final MaintenancePlanComputerRepository maintenancePlanComputerRepository;

    @Override
    public Page<MaintenancePlanComputerResponseDTO> findAllByMaintenancePlanId(int page, int size, Long maintenancePlanId) {
        Page<MaintenancePlanComputer> maintenancePlanComputers = maintenancePlanComputerRepository.findAllByMaintenancePlanId(
                maintenancePlanId,
                PageRequest.of(page, size, Sort.by("id").ascending()));
        if (maintenancePlanComputers.isEmpty())
            throw new NoContentException("MaintenancePlanComputers-Not-Content-204", HttpStatus.NOT_FOUND, "No MaintenancePlanComputers found");
        return maintenancePlanComputers.map(maintenancePlanComputerMapper::entityToResponseDTO);
    }

    @Override
    @Transactional
    public MaintenancePlanComputerResponseDTO create(MaintenancePlanComputerCreateDTO maintenancePlanComputerCreateDTO) {
        Computer computer = getComputer(maintenancePlanComputerCreateDTO.getComputerId());
        MaintenancePlan maintenancePlan = getMaintenancePlan(maintenancePlanComputerCreateDTO.getMaintenancePlanId());
        MaintenancePlanComputer maintenancePlanComputer = maintenancePlanComputerRepository.findByComputerIdAndMaintenancePlanId(
                maintenancePlanComputerCreateDTO.getComputerId(),
                maintenancePlanComputerCreateDTO.getMaintenancePlanId()
        ).orElse(null);

        if (maintenancePlanComputer != null)
            throw new BusinessException("MaintenancePlanComputer-Already-Exists-409", HttpStatus.CONFLICT, "MaintenancePlanComputer already exists");

        maintenancePlanComputer = maintenancePlanComputerMapper.createDTOToEntity(maintenancePlanComputerCreateDTO);
        maintenancePlanComputer.setState(MaintenancePlanningState.PENDING);
        maintenancePlanComputer.setComputer(computer);
        maintenancePlanComputer.setMaintenancePlan(maintenancePlan);
        return maintenancePlanComputerMapper.entityToResponseDTO(
                maintenancePlanComputerRepository.save(maintenancePlanComputer)
        );
    }

    @Override
    @Transactional
    public MaintenancePlanComputerResponseDTO update(MaintenancePlanComputerUpdateDTO maintenancePlanComputerUpdateDTO) {
        MaintenancePlanComputer maintenancePlanComputer = maintenancePlanComputerRepository.findById(maintenancePlanComputerUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "MaintenancePlanComputer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "MaintenancePlanComputer not found"
                ));
        maintenancePlanComputer.setDatePlanning(maintenancePlanComputerUpdateDTO.getDatePlanning());
        return maintenancePlanComputerMapper.entityToResponseDTO(
                maintenancePlanComputerRepository.save(maintenancePlanComputer));
    }

    @Override
    public void delete(Long id) {
        maintenancePlanComputerRepository.findById(id)
                .orElseThrow(() -> new NoContentException(
                        "MaintenancePlanComputer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "MaintenancePlanComputer not found"
                ));
        maintenancePlanComputerRepository.deleteById(id);
    }

    private Computer getComputer(Long id) {
        return computerRepository.findById(id)
                .orElseThrow(() -> new NoContentException(
                        "Computer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Computer not found"
                ));
    }

    private MaintenancePlan getMaintenancePlan(Long id) {
        return maintenancePlanRepository.findById(id)
                .orElseThrow(() -> new NoContentException(
                        "MaintenancePlan-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "MaintenancePlan not found"
                ));
    }

}