package co.com.assets_service.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import co.com.assets_service.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import co.com.assets_service.model.TypeComputer;
import co.com.assets_service.mapper.TypeComputerMapper;
import co.com.assets_service.dto.TypeComputerUpdateDTO;
import co.com.assets_service.dto.TypeComputerCreateDTO;
import co.com.assets_service.dto.TypeComputerResponseDTO;
import co.com.assets_service.service.TypeComputerService;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.exception.NoContentException;
import co.com.assets_service.repository.TypeComputerRepository;

@Service
@RequiredArgsConstructor
public class TypeComputerServiceImpl implements TypeComputerService {

    private final TypeComputerMapper typeComputerMapper;
    private final TypeComputerRepository typeComputerRepository;

    @Override
    public List<TypeComputerResponseDTO> getAllTypeComputers() {
        List<TypeComputer> typeComputerList = typeComputerRepository.findAll();
        if (typeComputerList.isEmpty())
            throw new NoContentException("TypeComputer-Not-Content-204", HttpStatus.NOT_FOUND, "No TypeComputer found");
        return typeComputerList.stream().map(typeComputerMapper::entityToResponseDTO).toList();
    }

    @Override
    @Transactional
    public TypeComputerResponseDTO createTypeComputer(TypeComputerCreateDTO typeComputerCreateDTO) {
        typeComputerCreateDTO.setName(Utils.normalizeName(typeComputerCreateDTO.getName()));
        validateUniqueName(typeComputerCreateDTO.getName(), null);
        return typeComputerMapper.entityToResponseDTO(
                typeComputerRepository.save(
                        typeComputerMapper.createDTOToEntity(typeComputerCreateDTO)
                )
        );
    }

    @Override
    @Transactional
    public TypeComputerResponseDTO updateTypeComputer(TypeComputerUpdateDTO typeComputerUpdateDTO) {
        typeComputerUpdateDTO.setName(Utils.normalizeName(typeComputerUpdateDTO.getName()));
        typeComputerRepository.findById(typeComputerUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "TypeComputer-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "TypeComputer not found"
                ));
        validateUniqueName(typeComputerUpdateDTO.getName(), typeComputerUpdateDTO.getId());
        return typeComputerMapper.entityToResponseDTO(
                typeComputerRepository.save(
                        typeComputerMapper.updateDTOToEntity(typeComputerUpdateDTO)
                )
        );
    }

    private void validateUniqueName(String name, Long currentId) {
        typeComputerRepository.findByName(name)
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