package co.com.assets_service.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Data
public class ComputerCreateDTO {

    @NotBlank(message = "The fields is required")
    private String name;

    @NotBlank(message = "The fields is required")
    private String serialNumber;

    @NotNull(message = "The fields is required")
    private Boolean isEnabled;

    @NotBlank(message = "The fields is required")
    private String model;

    @Positive(message = "The fields is required")
    @NotNull(message = "The fields is required")
    private Long stateId;

    @Positive(message = "The fields is required")
    @NotNull(message = "The fields is required")
    private Long typeComputerId;

    @Positive(message = "The fields is required")
    @NotNull(message = "The fields is required")
    private Long manufacturerId;

    @Positive(message = "The fields is required")
    @NotNull(message = "The fields is required")
    private Long employeeId;
}