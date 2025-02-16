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
import park_su_park.backend.exception.NotExistUserException;
import park_su_park.backend.exception.ValidateMemberException;
import park_su_park.backend.responseDto.ErrorResponse;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler{


    //중복 회원 처리
    @ExceptionHandler(ValidateMemberException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementFoundException(ValidateMemberException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
    }

    //조회시 username과 email모두 요규
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
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