package restoran.validation.vali;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import restoran.validation.ChefAgeValidation;

public class ChefAgeVali implements ConstraintValidator<ChefAgeValidation, Integer> {
@Override
public boolean isValid(Integer value, ConstraintValidatorContext context) {
    return value > 25 && 40 > value;

    }
}
