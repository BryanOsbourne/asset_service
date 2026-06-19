package co.com.assets_service.security;

import lombok.Data;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    @NotBlank(message = "The field is required")
    private String username;
    @NotBlank(message = "The field is required")
    private String password;
}