package park_su_park.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import park_su_park.backend.dto.responseBody.ApiResponseBody;
import park_su_park.backend.dto.responseBody.ValidationFailureData;
import park_su_park.backend.exception.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({DuplicateResourceException.class, IllegalArgumentException.class, IllegalStateException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiResponseBody> handleBadRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponseBody.create(e.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseBody> handleNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseBody.create(e.getMessage()));
    }

    @ExceptionHandler({UnauthorizedAccessException.class, AuthenticationFailedException.class})
    public ResponseEntity<ApiResponseBody> handleUnauthorized(Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponseBody.create(e.getMessage()));
    }

    @ExceptionHandler(ForbiddenAccessException.class)
    public ResponseEntity<ApiResponseBody> handleForbidden(Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponseBody.create(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseBody> handleRequestBodyNotValid(MethodArgumentNotValidException e) {
        Map<String, String> fieldErrors = new HashMap<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ValidationFailureData validationFailureData = ValidationFailureData.create(fieldErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponseBody.create("검증 실패", validationFailureData));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseBody> handleUnknownError(Exception e) {
        log.error("Unexpected error occurred : {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponseBody.create("서버 내부 오류 발생"));
    }
}
