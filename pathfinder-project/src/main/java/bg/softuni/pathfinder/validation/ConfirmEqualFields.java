package bg.softuni.pathfinder.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ConfirmEqualFieldsValidator.class)
public @interface ConfirmEqualFields {
    String field();
    String confirmField();

    String message() default "Password length must be between 5 and 20 characters and passwords should match.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
