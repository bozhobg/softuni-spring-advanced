package bg.softuni.pathfinder.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = VideoUrlValidator.class)
public @interface VideoUrl {

    String message() default "Video Url (only last eleven characters from Youtube)";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
