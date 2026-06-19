package co.com.assets_service.service;

import java.util.List;
import co.com.assets_service.dto.*;

public interface CostCenterService {
    List<CostCenterResponseDTO> getAllCostCenters();
    CostCenterResponseDTO createCostCenter(CostCenterCreateDTO costCenterCreateDTO);
    CostCenterResponseDTO updateCostCenter(CostCenterUpdateDTO costCenterUpdateDTO);
}