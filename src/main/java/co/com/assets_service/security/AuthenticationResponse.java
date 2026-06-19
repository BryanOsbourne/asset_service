package co.com.assets_service.security;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import co.com.assets_service.dto.UserBasicResponseDTO;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private UserBasicResponseDTO user;
}