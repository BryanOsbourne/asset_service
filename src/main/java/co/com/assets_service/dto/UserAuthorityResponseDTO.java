package co.com.assets_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserAuthorityResponseDTO {
    private Long id;
    private LocalDateTime dateCreation;
    private AuthorityResponseDTO authorityResponseDTO;
}