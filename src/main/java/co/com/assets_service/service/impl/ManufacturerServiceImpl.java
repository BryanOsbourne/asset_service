package co.com.assets_service.service.impl;

import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import co.com.assets_service.utils.Utils;
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
    @Transactional
    public ManufacturerResponseDTO createManufacturer(ManufacturerCreateDTO manufacturerCreateDTO) {
        manufacturerCreateDTO.setName(Utils.normalizeName(manufacturerCreateDTO.getName()));
        validateUniqueName(manufacturerCreateDTO.getName(), null);
        return manufacturerMapper.entityToResponseDTO(
                manufacturerRepository.save(
                        manufacturerMapper.createDTOToEntity(manufacturerCreateDTO)
                )
        );
    }

    @Override
    @Transactional
    public ManufacturerResponseDTO updateManufacturer(ManufacturerUpdateDTO manufacturerUpdateDTO) {
        manufacturerUpdateDTO.setName(Utils.normalizeName(manufacturerUpdateDTO.getName()));
        manufacturerRepository.findById(manufacturerUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "Manufacturer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Manufacturer not found"
                ));
        validateUniqueName(manufacturerUpdateDTO.getName(), manufacturerUpdateDTO.getId());
        return manufacturerMapper.entityToResponseDTO(
                manufacturerRepository.save(
                        manufacturerMapper.updateDTOToEntity(manufacturerUpdateDTO)
                )
        );
    }

    private void validateUniqueName(String name, Long currentId) {
        manufacturerRepository.findByName(name)
                .filter(c -> currentId == null || !c.getId().equals(currentId))
                .ifPresent(c -> {
                    throw new BusinessException(
                            "Computer-Conflict-409",
                            HttpStatus.CONFLICT,
                            "Computer name already exists"
                    );
                });
    }
}