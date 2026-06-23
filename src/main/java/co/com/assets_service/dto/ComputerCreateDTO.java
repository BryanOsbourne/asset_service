package co.com.assets_service.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Data
public class ComputerCreateDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "serialNumber is required")
    private String serialNumber;

    @NotNull(message = "isEnabled is required")
    private Boolean isEnabled;

    @NotBlank(message = "model is required")
    private String model;

    @Positive(message = "state center is required")
    @NotNull(message = "state center is required")
    private Long stateId;

    @Positive(message = "type computer is required")
    @NotNull(message = "type computer is required")
    private Long typeComputerId;

    @Positive(message = "type computer is required")
    @NotNull(message = "type computer is required")
    private Long manufacturerId;
}