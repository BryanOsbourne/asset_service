package co.com.assets_service.dto;

import lombok.Data;
import co.com.assets_service.enums.MaintenanceType;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class MaintenanceComputerResponseDTO {
    private Long id;
    private MaintenanceType type;
    @JsonProperty("maintenancePlanComputer")
    private MaintenancePlanComputerResponseDTO maintenancePlanComputerResponseDTO;
}