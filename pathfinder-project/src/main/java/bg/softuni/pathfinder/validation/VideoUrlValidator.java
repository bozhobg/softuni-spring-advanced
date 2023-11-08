package bg.softuni.pathfinder.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoUrlValidator implements ConstraintValidator<VideoUrl, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return Pattern.matches("^[-\\w]{11}$", value);
    }
}
