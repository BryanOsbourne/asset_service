package co.com.assets_service.service;

import java.util.List;
import co.com.assets_service.dto.MaintenanceActivityCreateDTO;
import co.com.assets_service.dto.MaintenanceActivityUpdateDTO;
import co.com.assets_service.dto.MaintenanceActivityResponseDTO;
import org.springframework.data.domain.Page;

public interface MaintenanceActivityService {
    Page<MaintenanceActivityResponseDTO> findAll(int page, int size);
    Page<MaintenanceActivityResponseDTO> findAllByMaintenanceComputerId(int page, int size, Long maintenancePlanComputerId);
    MaintenanceActivityResponseDTO createMaintenanceActivity(MaintenanceActivityCreateDTO maintenanceActivityCreateDTO);
    MaintenanceActivityResponseDTO updateMaintenanceActivity(MaintenanceActivityUpdateDTO maintenanceActivityUpdateDTO);
}