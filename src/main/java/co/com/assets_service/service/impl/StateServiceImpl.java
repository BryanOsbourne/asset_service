package co.com.assets_service.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import co.com.assets_service.utils.Utils;
import jakarta.transaction.Transactional;
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
    @Transactional
    public StateResponseDTO createState(StateCreateDTO stateCreateDTO) {
        stateCreateDTO.setName(Utils.normalizeName(stateCreateDTO.getName()));
        validateUniqueName(stateCreateDTO.getName(), null);
        return stateMapper.entityToResponseDTO(
                stateRepository.save(
                        stateMapper.createDTOToEntity(stateCreateDTO)
                )
        );
    }

    @Override
    @Transactional
    public StateResponseDTO updateState(StateUpdateDTO stateUpdateDTO) {
        stateUpdateDTO.setName(Utils.normalizeName(stateUpdateDTO.getName()));
        stateRepository.findById(stateUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "State-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "State not found"
                ));
        validateUniqueName(stateUpdateDTO.getName(), stateUpdateDTO.getId());
        return stateMapper.entityToResponseDTO(
                stateRepository.save(
                        stateMapper.updateDTOToEntity(stateUpdateDTO)
                )
        );
    }

    private void validateUniqueName(String name, Long currentId) {
        stateRepository.findByName(name)
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