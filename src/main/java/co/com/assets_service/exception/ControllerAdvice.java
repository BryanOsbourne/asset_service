package co.com.assets_service.exception;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;
import co.com.assets_service.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ErrorDTO.builder().code("RE-500").message(ex.getMessage()).timestamp(LocalDateTime.now()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDTO> handleRequestException(RequestException ex) {
        return new ResponseEntity<>(ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).timestamp(ex.getTimestamp()).build(), ex.getHttpStatus());
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorDTO> handleBusinessException(BusinessException ex) {
        return new ResponseEntity<>(ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).timestamp(ex.getTimestamp()).build(), ex.getHttpStatus());
    }

    @ExceptionHandler(value = NoContentException.class)
    public ResponseEntity<ErrorDTO> handleNoContentException(NoContentException ex) {
        return new ResponseEntity<>(ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).timestamp(ex.getTimestamp()).build(), ex.getHttpStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleGlobalException(Exception ex) {
        MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        ErrorDTO errorDTO = ErrorDTO.builder().code("RE-400").message(errors.toString()).timestamp(LocalDateTime.now()).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}