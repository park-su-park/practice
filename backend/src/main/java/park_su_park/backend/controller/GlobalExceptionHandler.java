package park_su_park.backend.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import park_su_park.backend.exception.LogInException;
import park_su_park.backend.exception.NotExistToDoException;
import park_su_park.backend.exception.NotExistUserException;
import park_su_park.backend.exception.ValidateException;
import park_su_park.backend.dto.responseDto.ErrorResponseDto;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler{


    //valid 오류 처리
    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<ErrorResponseDto> handleNoSuchElementFoundException(ValidateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDto(e.getMessage()));
    }

    @ExceptionHandler(LogInException.class)
    public ResponseEntity<ErrorResponseDto> handleLoginException(LogInException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDto(e.getMessage()));
    }



    @ExceptionHandler({NotExistUserException.class, NotExistToDoException.class})
    public ResponseEntity<ErrorResponseDto> NotExistUserExceptionException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(e.getMessage()));
    }

    //@valid 형식오류 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto>processValidationError(MethodArgumentNotValidException exception) {
    BindingResult bindingResult = exception.getBindingResult();

    System.out.println(bindingResult.getFieldError().getDefaultMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto(bindingResult.getFieldError().getDefaultMessage()));
    }
}