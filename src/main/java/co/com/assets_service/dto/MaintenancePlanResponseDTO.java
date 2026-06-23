package co.com.assets_service.dto;

import lombok.Data;

@Data
public class MaintenancePlanResponseDTO {
    private Long id;
    private String name;
    private Boolean isOpened;
}