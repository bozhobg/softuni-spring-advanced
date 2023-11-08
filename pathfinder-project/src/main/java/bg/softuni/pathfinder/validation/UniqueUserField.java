package bg.softuni.pathfinder.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueUserFieldValidator.class)
public @interface UniqueUserField {

    String forField();

    String message() default "Username is already occupied";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
