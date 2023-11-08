package bg.softuni.pathfinder.validation;

import bg.softuni.pathfinder.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUserFieldValidator implements ConstraintValidator<UniqueUserField, String> {

    private final UserRepository userRepository;

    private String fieldName;

    @Autowired
    public UniqueUserFieldValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueUserField constraintAnnotation) {
        this.fieldName = constraintAnnotation.forField();
    }

    @Override
    public boolean isValid(String fieldValue, ConstraintValidatorContext context) {
        boolean isNotExisting = false;

        switch (fieldName) {
            case "email" -> isNotExisting = !this.userRepository.existsUserByEmail(fieldValue);
            case "username" -> isNotExisting = !this.userRepository.existsUserByUsername(fieldValue);
        }

        return isNotExisting;
    }
}
