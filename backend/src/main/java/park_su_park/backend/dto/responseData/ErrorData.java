package park_su_park.backend.dto.responseData;

import jakarta.validation.ConstraintViolation;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
public class ErrorData implements ResponseData {

    private final Map<String, String> validationErrors = new LinkedHashMap<>();

    public static ErrorData of(Set<ConstraintViolation<?>> constraintViolations) {
        ErrorData errorData = new ErrorData();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String[] split = constraintViolation.getPropertyPath().toString().split("\\.");
            errorData.getValidationErrors()
                .put(split[split.length - 1], constraintViolation.getMessage());
        }
        return errorData;
    }

    public static ErrorData of(List<FieldError> fieldErrors) {
        ErrorData errorData = new ErrorData();
        for (FieldError fieldError : fieldErrors) {
            errorData.getValidationErrors()
                .put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errorData;
    }

}
