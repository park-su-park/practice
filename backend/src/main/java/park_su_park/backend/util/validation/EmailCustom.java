package park_su_park.backend.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import park_su_park.backend.util.constant.ValidationMessage;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Email(message = ValidationMessage.EMAIL_FORMAT_MESSAGE)
@NotBlank(message = ValidationMessage.REQUIRED_FIELD_MESSAGE)
public @interface EmailCustom {
    String message() default ValidationMessage.DEFAULT_EMAIL_VALIDATION_FAILED_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
