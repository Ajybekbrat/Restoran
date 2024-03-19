package restoran.validation.vali;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import restoran.validation.EmailValidation;
import restoran.validation.PasswordValidation;

public class PassVali implements ConstraintValidator<PasswordValidation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() >= 4;
    }
}
