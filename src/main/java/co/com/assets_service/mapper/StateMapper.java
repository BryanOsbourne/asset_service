package co.com.assets_service.mapper;

import org.mapstruct.Mapper;
import co.com.assets_service.model.State;
import co.com.assets_service.dto.StateUpdateDTO;
import co.com.assets_service.dto.StateCreateDTO;
import co.com.assets_service.dto.StateResponseDTO;

@Mapper(componentModel = "spring")
public interface StateMapper {
    State createDTOToEntity(StateCreateDTO stateCreateDTO);
    StateResponseDTO entityToResponseDTO(State state);
    State updateDTOToEntity(StateUpdateDTO stateUpdateDTO);
}