package co.com.assets_service.service.impl;

import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import co.com.assets_service.model.MaintenanceActivity;
import co.com.assets_service.model.MaintenanceComputer;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.exception.NoContentException;
import co.com.assets_service.enums.MaintenancePlanningState;
import co.com.assets_service.mapper.MaintenanceActivityMapper;
import co.com.assets_service.dto.MaintenanceActivityCreateDTO;
import co.com.assets_service.dto.MaintenanceActivityUpdateDTO;
import co.com.assets_service.service.MaintenanceActivityService;
import co.com.assets_service.dto.MaintenanceActivityResponseDTO;
import co.com.assets_service.repository.MaintenanceComputerRepository;
import co.com.assets_service.repository.MaintenanceActivityRepository;

@Service
@RequiredArgsConstructor
public class MaintenanceActivityServiceImpl implements MaintenanceActivityService {

    private final MaintenanceActivityMapper maintenanceActivityMapper;
    private final MaintenanceComputerRepository maintenanceComputerRepository;
    private final MaintenanceActivityRepository maintenanceActivityRepository;

    @Override
    public Page<MaintenanceActivityResponseDTO> findAllByMaintenanceComputerId(int page, int size, Long maintenanceComputerId) {
        Page<MaintenanceActivity> maintenancesActivities = maintenanceActivityRepository.findAllByMaintenanceComputerId(
                maintenanceComputerId,
                PageRequest.of(page, size, Sort.by("id").ascending()));
        if (maintenancesActivities.isEmpty())
            throw new NoContentException("MaintenancesActivities-Not-Content-204", HttpStatus.NOT_FOUND, "No MaintenancesActivities found");
        return maintenancesActivities.map(maintenanceActivityMapper::entityToResponseDTO);
    }

    @Override
    @Transactional
    public MaintenanceActivityResponseDTO createMaintenanceActivity(MaintenanceActivityCreateDTO maintenanceActivityCreateDTO) {
        MaintenanceComputer maintenanceComputer = getMaintenanceComputer(maintenanceActivityCreateDTO.getMaintenanceComputerId());
        if (maintenanceComputer.getMaintenancePlanComputer().getState() != MaintenancePlanningState.IN_PROGRESS)
            throw new BusinessException("MaintenanceActivity-400", HttpStatus.BAD_REQUEST, "The maintenance plan is not in progress");
        MaintenanceActivity maintenanceActivity = maintenanceActivityMapper.createDTOToEntity(maintenanceActivityCreateDTO);
        maintenanceActivity.setMaintenanceComputer(maintenanceComputer);
        return maintenanceActivityMapper.entityToResponseDTO(
                maintenanceActivityRepository.save(maintenanceActivity)
        );
    }

    @Override
    @Transactional
    public MaintenanceActivityResponseDTO updateMaintenanceActivity(MaintenanceActivityUpdateDTO maintenanceActivityUpdateDTO) {
        MaintenanceActivity maintenanceActivity = maintenanceActivityRepository.findById(maintenanceActivityUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "MaintenanceActivity-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "MaintenanceActivity not found"
                ));

        if (maintenanceActivity.getMaintenanceComputer().getMaintenancePlanComputer().getState() != MaintenancePlanningState.IN_PROGRESS)
            throw new BusinessException("MaintenanceActivity-400", HttpStatus.BAD_REQUEST, "The maintenance plan is not in progress");

        maintenanceActivity.setObservation(maintenanceActivityUpdateDTO.getObservation());
        maintenanceActivity.setMaintenanceTypeActivity(maintenanceActivityUpdateDTO.getMaintenanceTypeActivity());
        return maintenanceActivityMapper.entityToResponseDTO(
                maintenanceActivityRepository.save(
                        maintenanceActivity
                )
        );
    }

    private MaintenanceComputer getMaintenanceComputer(Long id) {
        return maintenanceComputerRepository.findById(id).orElseThrow(() -> new NoContentException(
                "MaintenanceComputer-Not-Found-404",
                HttpStatus.NOT_FOUND,
                "MaintenanceComputer not found"
        ));
    }

}