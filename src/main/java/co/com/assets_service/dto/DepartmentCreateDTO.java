package co.com.assets_service.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;

@Data
public class DepartmentCreateDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Positive(message = "Cost center is required")
    @NotNull(message = "Cost center is required")
    private Long costCenterId;
}