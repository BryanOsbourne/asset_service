package co.com.assets_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import co.com.assets_service.model.Manufacturer;
import org.springframework.data.domain.PageRequest;
import co.com.assets_service.dto.ManufacturerUpdateDTO;
import co.com.assets_service.dto.ManufacturerCreateDTO;
import co.com.assets_service.mapper.ManufacturerMapper;
import co.com.assets_service.dto.ManufacturerResponseDTO;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.service.ManufacturerService;
import co.com.assets_service.exception.NoContentException;
import co.com.assets_service.repository.ManufacturerRepository;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerMapper manufacturerMapper;
    private final ManufacturerRepository manufacturerRepository;

    @Override
    public Page<ManufacturerResponseDTO> getAllManufacturers(int page, int size) {
        Page<Manufacturer> manufacturers = manufacturerRepository.findAll(
                PageRequest.of(page, size, Sort.by("id").ascending()));
        if (manufacturers.isEmpty())
            throw new NoContentException("Manufacturers-Not-Content-204", HttpStatus.NOT_FOUND, "No Manufacturers found");
        return manufacturers.map(manufacturerMapper::entityToResponseDTO);
    }

    @Override
    public ManufacturerResponseDTO createManufacturer(ManufacturerCreateDTO manufacturerCreateDTO) {
        String name = manufacturerCreateDTO.getName().trim().toUpperCase();
        if (manufacturerRepository.existsByName(name)) {
            throw new BusinessException(
                    "Manufacturer-Conflict-409",
                    HttpStatus.CONFLICT,
                    "Manufacturer name already exists"
            );
        }
        manufacturerCreateDTO.setName(name);
        manufacturerCreateDTO.setIsEnabled(manufacturerCreateDTO.getIsEnabled());
        Manufacturer manufacturer = manufacturerRepository.save(
                manufacturerMapper.createDTOToEntity(manufacturerCreateDTO)
        );

        return manufacturerMapper.entityToResponseDTO(manufacturer);
    }

    @Override
    public ManufacturerResponseDTO updateManufacturer(ManufacturerUpdateDTO manufacturerUpdateDTO) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "Manufacturer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Manufacturer not found"
                ));

        String name = manufacturerUpdateDTO.getName().trim().toUpperCase();
        manufacturerRepository.findByName(name)
                .filter(existing -> !existing.getId().equals(manufacturer.getId()))
                .ifPresent(existing -> {
                    throw new BusinessException(
                            "Manufacturer-Conflict-409",
                            HttpStatus.CONFLICT,
                            "Manufacturer name already exists"
                    );
                });

        manufacturer.setName(name);
        manufacturer.setIsEnabled(manufacturerUpdateDTO.getIsEnable());
        Manufacturer updatedManufacturer = manufacturerRepository.save(manufacturer);
        return manufacturerMapper.entityToResponseDTO(updatedManufacturer);
    }
}