package co.com.assets_service.service;

import org.springframework.data.domain.Page;
import co.com.assets_service.dto.MaintenancePlanCreateDTO;
import co.com.assets_service.dto.MaintenancePlanUpdateDTO;
import co.com.assets_service.dto.MaintenancePlanResponseDTO;

public interface MaintenancePlanService {
    MaintenancePlanResponseDTO close(Long id);
    Page<MaintenancePlanResponseDTO> findAll(int page, int size);
    MaintenancePlanResponseDTO createMaintenancePlan(MaintenancePlanCreateDTO maintenancePlanCreateDTO);
    MaintenancePlanResponseDTO updateMaintenancePlan(MaintenancePlanUpdateDTO maintenancePlanUpdateDTO);
}