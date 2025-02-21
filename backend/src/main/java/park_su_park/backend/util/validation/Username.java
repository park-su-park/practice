package park_su_park.backend.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import park_su_park.backend.util.constant.ValidationMessage;
import park_su_park.backend.util.constant.ValidationRegExp;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Size(min = 5, max = 15, message = ValidationMessage.USERNAME_SIZE_MESSAGE)
@Pattern(regexp = ValidationRegExp.USERNAME_REGEXP, message = ValidationMessage.NO_WHITESPACE_ALLOWED)
public @interface Username {
    String message() default ValidationMessage.DEFAULT_USERNAME_VALIDATION_FAILED_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
