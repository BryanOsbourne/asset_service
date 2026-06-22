package co.com.assets_service.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeComputerUpdateDTO {

    @NotNull(message = "Id is required")
    @Positive(message = "Id is required")
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
}