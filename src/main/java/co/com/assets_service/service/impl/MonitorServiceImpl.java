package co.com.assets_service.service.impl;

import co.com.assets_service.model.*;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import co.com.assets_service.utils.Utils;
import co.com.assets_service.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import co.com.assets_service.dto.MonitorUpdateDTO;
import co.com.assets_service.dto.MonitorCreateDTO;
import co.com.assets_service.mapper.MonitorMapper;
import org.springframework.data.domain.PageRequest;
import co.com.assets_service.dto.MonitorResponseDTO;
import co.com.assets_service.service.MonitorService;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.exception.NoContentException;

@Service
@RequiredArgsConstructor
public class MonitorServiceImpl implements MonitorService {

    private final MonitorMapper monitorMapper;
    private final StateRepository stateRepository;
    private final MonitorRepository monitorRepository;
    private final InternalCodeRepository internalCodeRepository;
    private final ManufacturerRepository manufacturerRepository;

    @Override
    public Page<MonitorResponseDTO> findAll(int page, int size) {
        Page<Monitor> Monitors = monitorRepository.findAll(
                PageRequest.of(page, size, Sort.by("id").ascending()));
        if (Monitors.isEmpty())
            throw new NoContentException("Monitors-Not-Content-204", HttpStatus.NOT_FOUND, "No Monitors found");
        return Monitors.map(monitorMapper::entityToResponseDTO);
    }

    @Override
    @Transactional
    public MonitorResponseDTO createMonitor(MonitorCreateDTO monitorCreateDTO) {
        monitorCreateDTO.setName(Utils.normalizeName(monitorCreateDTO.getName()));
        InternalCode internalCode = new InternalCode();
        internalCode.setPrefix("SF-");
        internalCode.setSeries("000-");
        Monitor monitor = buildMonitorForCreate(monitorCreateDTO);
        monitor.setInternalCode(internalCodeRepository.save(internalCode));
        return monitorMapper.entityToResponseDTO(
                monitorRepository.save(monitor)
        );
    }

    @Override
    @Transactional
    public MonitorResponseDTO updateMonitor(MonitorUpdateDTO monitorUpdateDTO) {
        monitorUpdateDTO.setName(Utils.normalizeName(monitorUpdateDTO.getName()));
        Monitor monitor = buildMonitorForUpdate(monitorUpdateDTO);
        return monitorMapper.entityToResponseDTO(
                monitorRepository.save(monitor)
        );
    }

    private Monitor buildMonitorForCreate(MonitorCreateDTO monitorCreateDTO) {
        validateUniqueName(monitorCreateDTO.getName(), null);
        Monitor monitor = monitorMapper.createDTOToEntity(monitorCreateDTO);
        monitor.setState(getState(monitorCreateDTO.getStateId()));
        monitor.setManufacturer(getManufacturer(monitorCreateDTO.getManufacturerId()));
        return monitor;
    }

    private Monitor buildMonitorForUpdate(MonitorUpdateDTO monitorUpdateDTO) {
        monitorRepository.findById(monitorUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "Monitor-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Monitor not found"
                ));
        validateUniqueName(monitorUpdateDTO.getName(), monitorUpdateDTO.getId());
        Monitor monitor = monitorMapper.updateDTOToEntity(monitorUpdateDTO);
        monitor.setState(getState(monitorUpdateDTO.getStateId()));
        monitor.setManufacturer(getManufacturer(monitorUpdateDTO.getManufacturerId()));
        return monitor;
    }

    private void validateUniqueName(String name, Long currentId) {
        monitorRepository.findByName(name)
                .filter(c -> currentId == null || !c.getId().equals(currentId))
                .ifPresent(c -> {
                    throw new BusinessException(
                            "Monitor-Conflict-409",
                            HttpStatus.CONFLICT,
                            "Monitor name already exists"
                    );
                });
    }

    private Manufacturer getManufacturer(Long id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new NoContentException(
                        "Manufacturer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Manufacturer not found"
                ));
    }

    private State getState(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new NoContentException(
                        "State-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "State not found"
                ));
    }

}