package co.com.assets_service.dto;

import lombok.Data;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import co.com.assets_service.enums.MaintenancePlanningState;

@Data
public class MaintenancePlanComputerResponseDTO {
    private Long id;
    private LocalDateTime datePlanning;
    private LocalDateTime dateExecuted;
    private LocalDateTime dateCreation;
    @JsonProperty("computer")
    private ComputerResponseDTO computerResponseDTO;
    @JsonProperty("maintenance_plan")
    private MaintenancePlanResponseDTO maintenancePlanResponseDTO;
    private MaintenancePlanningState state;
}