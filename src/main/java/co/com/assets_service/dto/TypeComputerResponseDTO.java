package co.com.assets_service.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class TypeComputerResponseDTO {
    private Long id;
    private String name;
}