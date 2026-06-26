package co.com.assets_service.service;

import co.com.assets_service.dto.*;
import org.springframework.data.domain.Page;

public interface MaintenanceComputerService {
    MaintenanceComputerResponseDTO findByMaintenancePlanComputerId(Long maintenancePlanComputerId);
    MaintenanceComputerResponseDTO create(MaintenanceComputerCreateDTO maintenanceComputerCreateDTO);
    MaintenanceComputerResponseDTO update(MaintenanceComputerUpdateDTO maintenanceComputerUpdateDTO);
    Page<MaintenanceComputerResponseDTO> findAllMaintenancesByMaintenancePlanComputerId(int page, int size, Long maintenancePlanComputerId);
}