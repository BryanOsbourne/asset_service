package co.com.assets_service.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import co.com.assets_service.model.State;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import co.com.assets_service.dto.StateUpdateDTO;
import co.com.assets_service.dto.StateCreateDTO;
import co.com.assets_service.mapper.StateMapper;
import co.com.assets_service.dto.StateResponseDTO;
import co.com.assets_service.service.StateService;
import co.com.assets_service.repository.StateRepository;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.exception.NoContentException;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateMapper stateMapper;
    private final StateRepository stateRepository;

    @Override
    public List<StateResponseDTO> getAllStates() {
        List<State> stateList = stateRepository.findAll();
        if (stateList.isEmpty())
            throw new NoContentException("State-Not-Content-204", HttpStatus.NOT_FOUND, "No states found");
        return stateList.stream().map(stateMapper::entityToResponseDTO).toList();
    }

    @Override
    public StateResponseDTO createState(StateCreateDTO stateCreateDTO) {

        stateCreateDTO.setName(stateCreateDTO.getName().trim().toUpperCase());

        if (stateRepository.existsByName(stateCreateDTO.getName())) {
            throw new BusinessException(
                    "State-Conflict-409",
                    HttpStatus.CONFLICT,
                    "State name already exists"
            );
        }

        return stateMapper.entityToResponseDTO(
                stateRepository.save(
                        stateMapper.createDTOToEntity(stateCreateDTO)
                )
        );
    }

    @Override
    public StateResponseDTO updateState(StateUpdateDTO stateUpdateDTO) {

        stateUpdateDTO.setName(stateUpdateDTO.getName().trim().toUpperCase());

        stateRepository.findById(stateUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "State-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "State not found"
                ));

        stateRepository.findByName(stateUpdateDTO.getName())
                .filter(existing -> !existing.getId().equals(stateUpdateDTO.getId()))
                .ifPresent(existing -> {
                    throw new BusinessException(
                            "State-Conflict-409",
                            HttpStatus.CONFLICT,
                            "State name already exists"
                    );
                });

        return stateMapper.entityToResponseDTO(
                stateRepository.save(
                        stateMapper.updateDTOToEntity(stateUpdateDTO)
                )
        );
    }
}