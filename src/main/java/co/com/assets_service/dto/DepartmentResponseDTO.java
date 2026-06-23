package co.com.assets_service.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class DepartmentResponseDTO {
    private Long id;
    private String name;
    @JsonProperty("costCenter")
    private CostCenterResponseDTO costCenterResponseDTO;
}