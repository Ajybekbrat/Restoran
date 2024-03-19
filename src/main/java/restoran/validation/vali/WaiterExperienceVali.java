package restoran.validation.vali;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import restoran.validation.WaiterExperienceValidation;

public class WaiterExperienceVali implements ConstraintValidator<WaiterExperienceValidation, Integer> {
@Override
public boolean isValid(Integer value, ConstraintValidatorContext context) {
    return value >=1;

    }
}
