package co.com.assets_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MaintenancePlanResponseDTO {
    private Long id;
    private String name;
    private Boolean isOpened;
    private LocalDateTime dateClosed;
}