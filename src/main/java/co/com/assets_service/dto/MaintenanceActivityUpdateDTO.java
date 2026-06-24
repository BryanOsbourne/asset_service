package co.com.assets_service.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import co.com.assets_service.enums.MaintenanceTypeActivity;

@Data
public class MaintenanceActivityUpdateDTO {

    @NotNull(message = "The fields is required")
    @Positive(message = "The fields is required")
    private Long id;

    @NotBlank(message = "The fields is required")
    private String observation;

    @NotNull(message = "The fields is required")
    private MaintenanceTypeActivity maintenanceTypeActivity;
}