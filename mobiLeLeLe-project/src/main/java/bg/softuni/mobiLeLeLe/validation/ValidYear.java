package bg.softuni.mobiLeLeLe.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidYearValidator.class)
public @interface ValidYear {
    String message() default "Not a valid year";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
