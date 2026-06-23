package co.com.assets_service.dto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class ComputerResponseDTO {
    private Long id;
    private String name;
    private String model;
    private Boolean isEnabled;
    private String serialNumber;
    @JsonProperty("state")
    private StateResponseDTO stateResponseDTO;
    @JsonProperty("typeComputer")
    private TypeComputerResponseDTO typeComputerResponseDTO;
    @JsonProperty("manufacturer")
    private ManufacturerResponseDTO manufacturerResponseDTO;
    @JsonProperty("internalCode")
    private InternalCodeResponseDTO internalCodeResponseDTO;
}