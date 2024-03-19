package restoran.validation.vali;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import restoran.validation.ChefExperienceValidation;

public class ChefExperienceVali implements ConstraintValidator<ChefExperienceValidation, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value>=2;
    }
}
