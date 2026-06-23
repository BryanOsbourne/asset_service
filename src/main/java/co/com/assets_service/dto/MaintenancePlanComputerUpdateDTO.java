package co.com.assets_service.dto;

import lombok.Data;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class MaintenancePlanComputerUpdateDTO {

    @NotNull(message ="The field is required")
    @Positive(message = "The field is required")
    private Long id;

    @NotNull(message ="The field is required")
    private LocalDateTime datePlanning;
}