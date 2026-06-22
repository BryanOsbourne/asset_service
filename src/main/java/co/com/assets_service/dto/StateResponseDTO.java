package co.com.assets_service.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class StateResponseDTO {
    private Long id;
    private String name;
}