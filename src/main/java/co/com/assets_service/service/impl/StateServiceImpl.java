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
        String name = stateCreateDTO.getName().trim().toUpperCase();
        if (stateRepository.existsByName(name)) {
            throw new BusinessException(
                    "State-Conflict-409",
                    HttpStatus.CONFLICT,
                    "State name already exists"
            );
        }
        stateCreateDTO.setName(name);
        return stateMapper.entityToResponseDTO(
                stateRepository.save(
                        stateMapper.createDTOToEntity(stateCreateDTO)
                )
        );
    }

    @Override
    public StateResponseDTO updateState(StateUpdateDTO stateUpdateDTO) {
        State state = stateRepository.findById(stateUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "State-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "State not found"
                ));

        String name = stateUpdateDTO.getName().trim().toUpperCase();
        stateRepository.findByName(name)
                .filter(existing -> !existing.getId().equals(state.getId()))
                .ifPresent(existing -> {
                    throw new BusinessException(
                            "State-Conflict-409",
                            HttpStatus.CONFLICT,
                            "State name already exists"
                    );
                });

        state.setName(name);
        return stateMapper.entityToResponseDTO(stateRepository.save(state));
    }
}