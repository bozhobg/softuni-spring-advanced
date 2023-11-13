package bg.softuni.mobiLeLeLe.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class ValidYearValidator implements ConstraintValidator<ValidYear, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        return Year.now().compareTo(Year.of(value)) >= 0;
    }
}
