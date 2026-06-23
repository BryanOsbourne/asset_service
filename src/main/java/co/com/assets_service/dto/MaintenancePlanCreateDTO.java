package co.com.assets_service.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
public class MaintenancePlanCreateDTO {
    @NotBlank(message = "The fields is required")
    private String name;
    @NotNull(message = "The fields is required")
    private Boolean isOpened;
}