package co.com.assets_service.service;

import org.springframework.data.domain.Page;
import co.com.assets_service.dto.MonitorCreateDTO;
import co.com.assets_service.dto.MonitorUpdateDTO;
import co.com.assets_service.dto.MonitorResponseDTO;

public interface MonitorService {
    Page<MonitorResponseDTO> findAll(int page, int size);
    MonitorResponseDTO createMonitor(MonitorCreateDTO monitorCreateDTO);
    MonitorResponseDTO updateMonitor(MonitorUpdateDTO monitorUpdateDTO);
}