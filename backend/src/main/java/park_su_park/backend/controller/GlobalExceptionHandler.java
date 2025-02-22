package park_su_park.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import park_su_park.backend.dto.responseData.ApiResponseBody;
import park_su_park.backend.exception.ExpriedSessionException;
import park_su_park.backend.exception.LogInException;
import park_su_park.backend.exception.NotExistException;
import park_su_park.backend.exception.ValidateException;
import park_su_park.backend.exception.UsedException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandler {


    //valid 오류
    @ExceptionHandler({ValidateException.class, UsedException.class})
    public ResponseEntity<ApiResponseBody> handle_CONFLICT(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ApiResponseBody(e.getMessage(), null));
    }

    @ExceptionHandler({LogInException.class, ExpriedSessionException.class})
    public ResponseEntity<ApiResponseBody> handle_UNAUTHORIZED(RuntimeException e) {
        log.info("오류 발생 : {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ApiResponseBody(e.getMessage(), null));
    }


    @ExceptionHandler(NotExistException.class)
    public ResponseEntity<ApiResponseBody> handle_NOT_FOUND(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponseBody(e.getMessage(), null));
    }

    //@valid 형식오류 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseBody> processValidationError(
        MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        System.out.println(bindingResult.getFieldError().getDefaultMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ApiResponseBody(bindingResult.getFieldError().getDefaultMessage(), null));
    }
}