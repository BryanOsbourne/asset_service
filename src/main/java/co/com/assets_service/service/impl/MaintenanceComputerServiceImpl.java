package co.com.assets_service.service.impl;

import co.com.assets_service.dto.*;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import co.com.assets_service.model.MaintenanceComputer;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.exception.NoContentException;
import co.com.assets_service.model.MaintenancePlanComputer;
import co.com.assets_service.enums.MaintenancePlanningState;
import co.com.assets_service.mapper.MaintenanceComputerMapper;
import co.com.assets_service.service.MaintenanceComputerService;
import co.com.assets_service.repository.MaintenanceComputerRepository;
import co.com.assets_service.repository.MaintenancePlanComputerRepository;

@Service
@RequiredArgsConstructor
public class MaintenanceComputerServiceImpl implements MaintenanceComputerService {

    private final MaintenanceComputerMapper maintenanceComputerMapper;
    private final MaintenanceComputerRepository maintenanceComputerRepository;
    private final MaintenancePlanComputerRepository maintenancePlanComputerRepository;

    @Override
    public Page<MaintenanceComputerResponseDTO> findAllMaintenancesByMaintenancePlanComputerId(int page, int size, Long maintenancePlanComputerId) {
        Page<MaintenanceComputer> maintenances = maintenanceComputerRepository.findAllByMaintenancePlanComputerId(
                maintenancePlanComputerId,
                PageRequest.of(page, size, Sort.by("id").ascending()));
        if (maintenances.isEmpty())
            throw new NoContentException("Maintenances-Not-Content-204", HttpStatus.NOT_FOUND, "No Maintenances found");
        return maintenances.map(maintenanceComputerMapper::entityToResponseDTO);
    }

    @Override
    @Transactional
    public MaintenanceComputerResponseDTO create(MaintenanceComputerCreateDTO maintenanceComputerCreateDTO) {
        MaintenancePlanComputer maintenancePlanComputer = getMaintenancePlanComputer(maintenanceComputerCreateDTO.getMaintenancePlanComputerId());
        MaintenanceComputer maintenanceComputer = maintenanceComputerRepository.findByMaintenancePlanComputerId(
                maintenanceComputerCreateDTO.getMaintenancePlanComputerId()
        ).orElse(null);

        if (maintenanceComputer != null)
            throw new BusinessException("MaintenancePlanComputer-Already-Exists-409", HttpStatus.CONFLICT, "MaintenancePlanComputer already exists");

        maintenanceComputer = maintenanceComputerMapper.createDTOToEntity(maintenanceComputerCreateDTO);
        maintenanceComputer.setMaintenancePlanComputer(maintenancePlanComputer);
        maintenanceComputer.getMaintenancePlanComputer().setState(MaintenancePlanningState.IN_PROGRESS);
        return maintenanceComputerMapper.entityToResponseDTO(
                maintenanceComputerRepository.save(maintenanceComputer)
        );
    }

    @Override
    @Transactional
    public MaintenanceComputerResponseDTO update(MaintenanceComputerUpdateDTO maintenanceComputerUpdateDTO) {
        MaintenanceComputer maintenanceComputer = maintenanceComputerRepository.findById(maintenanceComputerUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "MaintenancePlanComputer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "MaintenancePlanComputer not found"
                ));

        MaintenancePlanComputer maintenancePlanComputer = getMaintenancePlanComputer(maintenanceComputer.getMaintenancePlanComputer().getId());
        if (maintenancePlanComputer.getState() == MaintenancePlanningState.COMPLETED)
            throw new BusinessException("MaintenancePlanComputer-Completed-400", HttpStatus.BAD_REQUEST, "MaintenancePlanComputer is completed");

        maintenanceComputer.setType(maintenanceComputerUpdateDTO.getType());
        return maintenanceComputerMapper.entityToResponseDTO(
                maintenanceComputerRepository.save(maintenanceComputer)
        );
    }

    private MaintenancePlanComputer getMaintenancePlanComputer(Long id) {
        return maintenancePlanComputerRepository.findById(id)
                .orElseThrow(() -> new NoContentException(
                        "MaintenancePlanComputer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "MaintenancePlanComputer not found"
                ));
    }

}