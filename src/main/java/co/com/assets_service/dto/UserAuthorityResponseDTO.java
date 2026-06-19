package co.com.assets_service.dto;

import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;

@Data
@Builder
public class UserAuthorityResponseDTO {
    private Long id;
    private LocalDateTime dateCreation;
    private AuthorityResponseDTO authorityResponseDTO;
}