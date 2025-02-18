package park_su_park.backend.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import park_su_park.backend.exception.LogInException;
import park_su_park.backend.exception.NotExistUserException;
import park_su_park.backend.exception.ValidateException;
import park_su_park.backend.dto.responseDto.ErrorResponse;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler{


    //valid 오류 처리
    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementFoundException(ValidateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(LogInException.class)
    public ResponseEntity<ErrorResponse> handleLoginException(LogInException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
    }



    @ExceptionHandler(NotExistUserException.class)
    public ResponseEntity<ErrorResponse> NotExistUserExceptionException(NotExistUserException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }

    //@valid 형식오류 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>processValidationError(MethodArgumentNotValidException exception) {
    BindingResult bindingResult = exception.getBindingResult();

    System.out.println(bindingResult.getFieldError().getDefaultMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(bindingResult.getFieldError().getDefaultMessage()));
    }
}