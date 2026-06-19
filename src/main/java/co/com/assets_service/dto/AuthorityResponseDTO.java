package co.com.assets_service.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class AuthorityResponseDTO {
    private Long id;
    private String code;
    private String description;
}