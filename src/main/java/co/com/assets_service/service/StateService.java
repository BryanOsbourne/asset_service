package co.com.assets_service.service;

import java.util.List;
import co.com.assets_service.dto.StateUpdateDTO;
import co.com.assets_service.dto.StateCreateDTO;
import co.com.assets_service.dto.StateResponseDTO;

public interface StateService {
    List<StateResponseDTO> getAllStates();
    StateResponseDTO createState(StateCreateDTO stateCreateDTO);
    StateResponseDTO updateState(StateUpdateDTO stateUpdateDTO);
}