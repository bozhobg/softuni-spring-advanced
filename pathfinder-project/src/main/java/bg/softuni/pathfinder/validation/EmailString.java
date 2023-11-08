package bg.softuni.pathfinder.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = EmailStringValidator.class)
public @interface EmailString {

    String message() default "Should be a valid email format!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
