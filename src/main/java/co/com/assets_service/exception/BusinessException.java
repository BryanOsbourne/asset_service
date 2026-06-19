package co.com.assets_service.exception;

import lombok.Data;
import java.time.ZoneId;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private String code;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;

    public BusinessException(String code, HttpStatus httpStatus, String message) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now(ZoneId.of("UTC"));
    }

}