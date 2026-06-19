package co.com.assets_service.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class UserBasicResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private Boolean isEnabled;
}