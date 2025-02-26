package park_su_park.backend.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.hibernate.validator.constraints.Length;
import park_su_park.backend.util.constant.ValidationMessage;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Length(min = 1, max = 30, message = ValidationMessage.MAX_TITLE_SIZE_MESSAGE)
public @interface Title {
    String message() default ValidationMessage.MAX_TITLE_SIZE_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
