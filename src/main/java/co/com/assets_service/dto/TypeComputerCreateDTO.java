package co.com.assets_service.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class TypeComputerCreateDTO {
    @NotBlank(message = "Name is required")
    private String name;
}