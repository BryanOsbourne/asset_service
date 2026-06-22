package co.com.assets_service.service;

import org.springframework.data.domain.Page;
import co.com.assets_service.dto.ManufacturerUpdateDTO;
import co.com.assets_service.dto.ManufacturerCreateDTO;
import co.com.assets_service.dto.ManufacturerResponseDTO;

public interface ManufacturerService {
    Page<ManufacturerResponseDTO> getAllManufacturers(int page, int size);
    ManufacturerResponseDTO createManufacturer(ManufacturerCreateDTO manufacturerCreateDTO);
    ManufacturerResponseDTO updateManufacturer(ManufacturerUpdateDTO manufacturerUpdateDTO);
}