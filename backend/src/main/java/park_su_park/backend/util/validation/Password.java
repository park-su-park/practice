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
@Size(min = 8, max = 20, message = ValidationMessage.PASSWORD_SIZE_MESSAGE)
@Pattern(regexp = ValidationRegExp.PASSWORD_REGEXP, message = ValidationMessage.PASSWORD_PATTER_MESSAGE)
public @interface Password {
    String message() default ValidationMessage.DEFAULT_PASSWORD_VALIDATION_FAILED_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
