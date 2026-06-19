package co.com.assets_service.service;

import java.util.List;
import co.com.assets_service.dto.StateUpdateDTO;
import co.com.assets_service.dto.StateCreateDTO;
import co.com.assets_service.dto.StateBasicResponseDTO;

public interface StateService {
    List<StateBasicResponseDTO> getAllStates();
    StateBasicResponseDTO createState(StateCreateDTO stateCreateDTO);
    StateBasicResponseDTO updateState(StateUpdateDTO stateUpdateDTO);
}