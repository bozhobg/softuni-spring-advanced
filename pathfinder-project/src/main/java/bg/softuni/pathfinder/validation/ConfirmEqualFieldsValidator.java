package bg.softuni.pathfinder.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.lang.annotation.Annotation;
import java.util.Objects;

public class ConfirmEqualFieldsValidator implements ConstraintValidator<ConfirmEqualFields, Object> {

    private String forFieldName;
    private String confirmFieldName;
    private String message;


    @Override
    public void initialize(ConfirmEqualFields constraintAnnotation) {
        this.forFieldName = constraintAnnotation.field();
        this.confirmFieldName = constraintAnnotation.confirmField();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        Object forProp = beanWrapper.getPropertyValue(forFieldName);
        Object confirmProp = beanWrapper.getPropertyValue(confirmFieldName);

        boolean isValid = Objects.equals(forProp, confirmProp);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addNode(confirmFieldName)
                    .addConstraintViolation();
        }

        return isValid;
    }
}
