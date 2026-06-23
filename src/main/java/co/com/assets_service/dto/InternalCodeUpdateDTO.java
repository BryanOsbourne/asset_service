package co.com.assets_service.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Data
public class InternalCodeUpdateDTO {

    @NotNull(message = "id is required")
    @Positive(message = "id is required")
    private Long id;
    
    @NotBlank(message = "prefix is required")
    private String prefix;

    @NotBlank(message = "series is required")
    private String series;
}