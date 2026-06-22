package co.com.assets_service.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerUpdateDTO {
    @NotNull(message = "The field is required")
    @Positive(message = "The field is required")
    private Long id;
    @NotBlank(message = "The field is required")
    private String name;
    @NotNull(message = "The field is required")
    private Boolean isEnable;
}