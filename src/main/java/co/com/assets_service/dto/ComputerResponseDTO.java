package co.com.assets_service.dto;

import lombok.Data;
import lombok.Builder;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
public class ComputerResponseDTO {
    private Long id;
    private String name;
    private String serialNumber;
    private Boolean isEnabled;
    private String model;
    private String internalCode;
    @JsonProperty("state")
    private StateResponseDTO stateResponseDTO;
    @JsonProperty("typeComputer")
    private TypeComputerResponseDTO typeComputerResponseDTO;
    @JsonProperty("manufacturer")
    private ManufacturerResponseDTO manufacturerResponseDTO;
}