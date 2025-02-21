package park_su_park.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import park_su_park.backend.dto.responseBody.ApiResponseBody;
import park_su_park.backend.exception.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({DuplicateResourceException.class, IllegalArgumentException.class, MethodArgumentNotValidException.class, IllegalStateException.class})
    public ResponseEntity<ApiResponseBody> handleBadRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponseBody.failure(e.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseBody> handleNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseBody.failure(e.getMessage()));
    }

    @ExceptionHandler({UnauthorizedAccessException.class, AuthenticationFailedException.class})
    public ResponseEntity<ApiResponseBody> handleUnauthorized(Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponseBody.failure(e.getMessage()));
    }

    @ExceptionHandler(ForbiddenAccessException.class)
    public ResponseEntity<ApiResponseBody> handleForbidden(Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResponseBody.failure(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseBody> handleUnknownError(Exception e) {
        log.error("Unexpected error occurred : {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponseBody.failure("서버 내부 오류 발생"));
    }
}
