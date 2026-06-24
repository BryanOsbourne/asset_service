package co.com.assets_service.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import co.com.assets_service.enums.MaintenanceTypeActivity;

@Data
public class MaintenanceActivityResponseDTO {
    private Long id;
    private String observation;
    private MaintenanceTypeActivity maintenanceTypeActivity;
    @JsonProperty("maintenance_computer")
    private MaintenanceComputerResponseDTO maintenanceComputerResponseDTO;
}