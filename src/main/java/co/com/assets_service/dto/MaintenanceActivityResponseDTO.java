package co.com.assets_service.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import co.com.assets_service.enums.MaintenanceTypeActivity;

import java.time.LocalDateTime;

@Data
public class MaintenanceActivityResponseDTO {
    private Long id;
    private String observation;
    private LocalDateTime dateCreation;
    private MaintenanceTypeActivity maintenanceTypeActivity;
    @JsonProperty("maintenanceComputer")
    private MaintenanceComputerResponseDTO maintenanceComputerResponseDTO;
}