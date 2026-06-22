package co.com.assets_service.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalCodeCreateDTO {

    @NotBlank(message = "prefix is required")
    private String prefix;

    @NotBlank(message = "series is required")
    private String series;
}