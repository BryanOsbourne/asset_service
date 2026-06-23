package co.com.assets_service.dto;

import lombok.Data;

@Data
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private String lastName;
    private String secondLastName;
    private Boolean isEnabled;
    private Boolean isTechnical;
}