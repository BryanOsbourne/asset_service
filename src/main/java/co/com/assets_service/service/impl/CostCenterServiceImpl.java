package co.com.assets_service.service.impl;

import java.util.List;
import co.com.assets_service.dto.*;
import lombok.RequiredArgsConstructor;
import jakarta.transaction.Transactional;
import co.com.assets_service.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import co.com.assets_service.model.CostCenter;
import co.com.assets_service.mapper.CostCenterMapper;
import co.com.assets_service.service.CostCenterService;
import co.com.assets_service.exception.BusinessException;
import co.com.assets_service.exception.NoContentException;
import co.com.assets_service.repository.CostCenterRepository;

@Service
@RequiredArgsConstructor
public class CostCenterServiceImpl implements CostCenterService {

    private final CostCenterMapper costCenterMapper;
    private final CostCenterRepository costCenterRepository;

    @Override
    public List<CostCenterResponseDTO> getAllCostCenters() {
        List<CostCenter> costCenterList = costCenterRepository.findAll();
        if (costCenterList.isEmpty())
            throw new NoContentException("CostCenter-Not-Content-204", HttpStatus.NOT_FOUND, "No costCenter found");
        return costCenterList.stream().map(costCenterMapper::entityToResponseDTO).toList();
    }

    @Override
    @Transactional
    public CostCenterResponseDTO createCostCenter(CostCenterCreateDTO costCenterCreateDTO) {
        costCenterCreateDTO.setName(Utils.normalizeName(costCenterCreateDTO.getName()));
        validateUniqueName(costCenterCreateDTO.getName(), null);
        return costCenterMapper.entityToResponseDTO(
                costCenterRepository.save(
                        costCenterMapper.createDTOToEntity(costCenterCreateDTO)
                )
        );
    }

    @Override
    @Transactional
    public CostCenterResponseDTO updateCostCenter(CostCenterUpdateDTO costCenterUpdateDTO) {
        costCenterUpdateDTO.setName(costCenterUpdateDTO.getName().trim().toUpperCase());
        costCenterRepository.findById(costCenterUpdateDTO.getId())
                .orElseThrow(() -> new NoContentException(
                        "CostCenter-Not-Found-404",
                        HttpStatus.NOT_FOUND,
                        "CostCenter not found"
                ));
        validateUniqueName(costCenterUpdateDTO.getName(), costCenterUpdateDTO.getId());
        return costCenterMapper.entityToResponseDTO(
                costCenterRepository.save(
                        costCenterMapper.updateDTOToEntity(costCenterUpdateDTO)
                )
        );
    }

    private void validateUniqueName(String name, Long currentId) {
        costCenterRepository.findByName(name)
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