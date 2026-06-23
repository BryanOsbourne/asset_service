package co.com.assets_service.service.impl;

import co.com.assets_service.model.*;
import lombok.RequiredArgsConstructor;
import co.com.assets_service.repository.*;
import co.com.assets_service.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.dto.MaintenancePlanCreateDTO;
import co.com.assets_service.dto.MaintenancePlanUpdateDTO;
import co.com.assets_service.exception.NoContentException;
import co.com.assets_service.mapper.MaintenancePlanMapper;
import co.com.assets_service.dto.MaintenancePlanResponseDTO;
import co.com.assets_service.service.MaintenancePlanService;

@Service
@RequiredArgsConstructor
public class MaintenancePlanServiceImpl implements MaintenancePlanService {

    private final MaintenancePlanMapper maintenancePlanMapper;
    private final MaintenancePlanRepository maintenancePlanRepository;

    @Override
    public Page<MaintenancePlanResponseDTO> findAll(int page, int size) {
        Page<MaintenancePlan> maintenancePlans = maintenancePlanRepository.findAll(
                PageRequest.of(page, size, Sort.by("id").ascending()));
        if (maintenancePlans.isEmpty())
            throw new NoContentException("maintenancePlans-Not-Content-204", HttpStatus.NOT_FOUND, "No MaintenancePlans found");
        return maintenancePlans.map(maintenancePlanMapper::entityToResponseDTO);
    }

    @Override
    @Transactional
    public MaintenancePlanResponseDTO createMaintenancePlan(MaintenancePlanCreateDTO maintenancePlanCreateDTO) {
        maintenancePlanCreateDTO.setName(Utils.normalizeName(maintenancePlanCreateDTO.getName()));
        validateUniqueName(maintenancePlanCreateDTO.getName(), null);
        return maintenancePlanMapper.entityToResponseDTO(
                maintenancePlanRepository.save(
                        maintenancePlanMapper.createDTOToEntity(maintenancePlanCreateDTO)
                )
        );
    }

    @Override
    @Transactional
    public MaintenancePlanResponseDTO updateMaintenancePlan(MaintenancePlanUpdateDTO maintenancePlanUpdateDTO) {
        maintenancePlanUpdateDTO.setName(Utils.normalizeName(maintenancePlanUpdateDTO.getName()));
        maintenancePlanRepository.findById(maintenancePlanUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "MaintenancePlan-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "MaintenancePlan not found"
                ));
        validateUniqueName(maintenancePlanUpdateDTO.getName(), maintenancePlanUpdateDTO.getId());
        return maintenancePlanMapper.entityToResponseDTO(
                maintenancePlanRepository.save(
                        maintenancePlanMapper.updateDTOToEntity(maintenancePlanUpdateDTO)
                )
        );
    }

    private void validateUniqueName(String name, Long currentId) {
        maintenancePlanRepository.findByName(name)
                .filter(c -> currentId == null || !c.getId().equals(currentId))
                .ifPresent(c -> {
                    throw new BusinessException(
                            "MaintenancePlan-Conflict-409",
                            HttpStatus.CONFLICT,
                            "MaintenancePlan already exists"
                    );
                });
    }

}