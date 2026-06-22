package co.com.assets_service.service.impl;

import co.com.assets_service.model.*;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import co.com.assets_service.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import co.com.assets_service.mapper.ComputerMapper;
import co.com.assets_service.dto.ComputerCreateDTO;
import co.com.assets_service.dto.ComputerUpdateDTO;
import co.com.assets_service.dto.ComputerResponseDTO;
import co.com.assets_service.service.ComputerService;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.exception.NoContentException;

@Service
@RequiredArgsConstructor
public class ComputerServiceImpl implements ComputerService {

    private final ComputerMapper computerMapper;
    private final StateRepository stateRepository;
    private final ComputerRepository computerRepository;
    private final InternalCodeRepository internalCodeRepository;
    private final TypeComputerRepository typeComputerRepository;
    private final ManufacturerRepository manufacturerRepository;

    @Override
    public Page<ComputerResponseDTO> findAll(int page, int size) {
        Page<Computer> computers = computerRepository.findAll(
                PageRequest.of(page, size, Sort.by("id").ascending()));
        if (computers.isEmpty())
            throw new NoContentException("computers-Not-Content-204", HttpStatus.NOT_FOUND, "No Computers found");
        return computers.map(computerMapper::entityToResponseDTO);
    }

    @Override
    @Transactional
    public ComputerResponseDTO createComputer(ComputerCreateDTO computerCreateDTO) {

        computerCreateDTO.setName(computerCreateDTO.getName().trim().toUpperCase());

        if (computerRepository.existsByName(computerCreateDTO.getName())) {
            throw new BusinessException(
                    "Computer-Conflict-409",
                    HttpStatus.CONFLICT,
                    "Computer name already exists"
            );
        }

        Manufacturer manufacturer = manufacturerRepository.findById(computerCreateDTO.getManufacturerId()).orElseThrow(
                () -> new NoContentException(
                        "Manufacturer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Manufacturer not found"
                )
        );

        TypeComputer typeComputer = typeComputerRepository.findById(computerCreateDTO.getTypeComputerId()).orElseThrow(
                () -> new NoContentException(
                        "TypeComputer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Type computer not found"
                )
        );

        State state = stateRepository.findById(computerCreateDTO.getStateId()).orElseThrow(
                () -> new NoContentException(
                        "State-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "State not found"
                )
        );

        InternalCode internalCode = new InternalCode();
        internalCode.setPrefix("SF-");
        internalCode.setSeries("000-");

        Computer computer = computerMapper.createDTOToEntity(computerCreateDTO);
        computer.setState(state);
        computer.setTypeComputer(typeComputer);
        computer.setManufacturer(manufacturer);
        computer.setInternalCode(internalCodeRepository.save(internalCode));

        return computerMapper.entityToResponseDTO(
                computerRepository.save(computer)
        );
    }

    @Override
    public ComputerResponseDTO updateComputer(ComputerUpdateDTO computerUpdateDTO) {

        computerUpdateDTO.setName(computerUpdateDTO.getName().trim().toUpperCase());

        Computer c = computerRepository.findById(computerUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "Computer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Computer not found"
                ));

        computerRepository.findByName(computerUpdateDTO.getName())
                .filter(existing -> !existing.getId().equals(computerUpdateDTO.getId()))
                .ifPresent(existing -> {
                    throw new BusinessException(
                            "Computer-Conflict-409",
                            HttpStatus.CONFLICT,
                            "Computer name already exists"
                    );
                });

        Manufacturer manufacturer = manufacturerRepository.findById(computerUpdateDTO.getManufacturerId()).orElseThrow(
                () -> new NoContentException(
                        "Manufacturer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Manufacturer not found"
                )
        );

        TypeComputer typeComputer = typeComputerRepository.findById(computerUpdateDTO.getTypeComputerId()).orElseThrow(
                () -> new NoContentException(
                        "TypeComputer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "Type computer not found"
                )
        );

        State state = stateRepository.findById(computerUpdateDTO.getStateId()).orElseThrow(
                () -> new NoContentException(
                        "State-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "State not found"
                )
        );

        Computer computer = computerMapper.updateDTOToEntity(computerUpdateDTO);
        computer.setState(state);
        computer.setTypeComputer(typeComputer);
        computer.setManufacturer(manufacturer);
        computer.setInternalCode(c.getInternalCode());

        return computerMapper.entityToResponseDTO(
                computerRepository.save(computer)
        );
    }
}