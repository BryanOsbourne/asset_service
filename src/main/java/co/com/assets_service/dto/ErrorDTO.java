package co.com.assets_service.dto;

import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDTO {
    private String code;
    private String message;
    private LocalDateTime timestamp;
}