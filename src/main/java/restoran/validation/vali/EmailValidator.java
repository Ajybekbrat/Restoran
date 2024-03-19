package restoran.validation.vali;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import restoran.validation.EmailValidation;
import restoran.validation.WaiterValidation;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.contains("@gmail.com");
    }
}
