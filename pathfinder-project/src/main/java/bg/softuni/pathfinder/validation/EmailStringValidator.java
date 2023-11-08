package bg.softuni.pathfinder.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailStringValidator implements ConstraintValidator<EmailString, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regex = "^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }
}
