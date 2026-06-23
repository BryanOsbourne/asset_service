package co.com.assets_service.dto;

import lombok.Data;

@Data
public class UserBasicResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private Boolean isEnabled;
}