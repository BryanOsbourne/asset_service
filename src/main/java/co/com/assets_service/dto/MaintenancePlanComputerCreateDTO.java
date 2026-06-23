package co.com.assets_service.dto;

import lombok.Data;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class MaintenancePlanComputerCreateDTO {

    @NotNull(message ="The field is required")
    private LocalDateTime datePlanning;

    @NotNull(message ="The field is required")
    @Positive(message = "The field is required")
    private Long computerId;

    @NotNull(message ="The field is required")
    @Positive(message = "The field is required")
    private Long maintenancePlanId;
}