package co.com.assets_service.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class CostCenterCreateDTO {
    @NotNull(message = "The field is required")
    private String name;
}