package co.com.assets_service.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import co.com.assets_service.enums.MaintenanceTypeActivity;

@Data
public class MaintenanceActivityCreateDTO {

    @NotBlank(message = "The fields is required")
    private String observation;

    @NotNull(message = "The fields is required")
    private MaintenanceTypeActivity maintenanceTypeActivity;

    @NotNull(message = "The fields is required")
    @Positive(message = "The fields is required")
    private Long maintenanceComputerId;
}