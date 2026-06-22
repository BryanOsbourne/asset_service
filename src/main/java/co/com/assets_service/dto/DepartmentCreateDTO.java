package co.com.assets_service.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentCreateDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Cost center is required")
    private CostCenterResponseDTO costCenter;
}