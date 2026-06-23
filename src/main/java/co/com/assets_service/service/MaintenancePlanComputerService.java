package co.com.assets_service.service;

import org.springframework.data.domain.Page;
import co.com.assets_service.dto.MaintenancePlanComputerCreateDTO;
import co.com.assets_service.dto.MaintenancePlanComputerUpdateDTO;
import co.com.assets_service.dto.MaintenancePlanComputerResponseDTO;

public interface MaintenancePlanComputerService {
    Page<MaintenancePlanComputerResponseDTO> findAllByMaintenancePlanId(int page, int size, Long userId);
    MaintenancePlanComputerResponseDTO create(MaintenancePlanComputerCreateDTO maintenancePlanComputerCreateDTO);
    MaintenancePlanComputerResponseDTO update(MaintenancePlanComputerUpdateDTO maintenancePlanComputerUpdateDTO);
    void delete(Long id);
}