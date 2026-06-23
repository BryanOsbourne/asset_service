package co.com.assets_service.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class ManufacturerCreateDTO {

    @NotBlank(message = "The field is required")
    private String name;

    @NotNull(message = "The field is required")
    private Boolean isEnabled;
}