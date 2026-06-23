package co.com.assets_service.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateDTO {
    @NotNull(message = "The fields is required")
    @Positive(message = "The fields is required")
    private Long id;
    @NotBlank(message = "The fields is required")
    private String name;
    @NotBlank(message = "The fields is required")
    private String lastName;
    @NotBlank(message = "The fields is required")
    private String secondLastName;
    @NotNull(message = "The fields is required")
    private Boolean isEnabled;
    @NotNull(message = "The fields is required")
    private Boolean isTechnical;
}