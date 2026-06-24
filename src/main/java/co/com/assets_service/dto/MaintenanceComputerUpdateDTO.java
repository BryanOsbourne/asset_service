package co.com.assets_service.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import co.com.assets_service.enums.MaintenanceType;

@Data
public class MaintenanceComputerUpdateDTO {

    @NotNull(message = "The field is required")
    @Positive(message = "The field is required")
    private Long id;

    @NotNull(message = "The field is required")
    private MaintenanceType type;
}