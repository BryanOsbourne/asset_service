package co.com.assets_service.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class InternalCodeCreateDTO {

    @NotBlank(message = "prefix is required")
    private String prefix;

    @NotBlank(message = "series is required")
    private String series;
}