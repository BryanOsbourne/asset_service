package co.com.assets_service.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerCreateDTO {

    @NotBlank(message = "The field is required")
    private String name;

    @NotNull(message = "The field is required")
    private Boolean isEnabled;
}